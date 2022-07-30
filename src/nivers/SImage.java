/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nivers;

/**
 *
 * @author 8741417
 */
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class SImage
{

  public static final String JPEG = "jpg";
  public static final String PNG = "png";
  private int srcHeight = 0;
  private int srcWidth = 0;
  private int height = 0;
  private int width = 0;
  private double scale = 0.0;
  private boolean resized = false;
  private BufferedImage bimage;

  public SImage( String filename )
  {
    this( filename, 1.0 );
  }

  public SImage( String filename, double scale )
  {
    if ( filename == null || filename.length() == 0 ){ throw new RuntimeException( "Digite o nome do arquivo!" ); }
    File src = new File( filename );
    if ( ! src.exists() ){ throw new RuntimeException( "Arquivo Inexistente!" ); }
    try
    {
      bimage = ImageIO.read( src );
    }
    catch ( Exception ex )
    {
      throw new RuntimeException( ex );
    }
    srcHeight = bimage.getHeight();
    srcWidth = bimage.getWidth();
    setScale( scale );
  }

  public void write( String filename, String format )
  {
    if ( filename == null || filename.length() == 0 ){ throw new RuntimeException( "Digite o nome do arquivo!" ); }
    if ( ! JPEG.equals( format ) && ! PNG.equals( format ) ){ throw new RuntimeException( "Formato inválido!" ); }

    BufferedImage bimage2 = new BufferedImage( width, height, BufferedImage.TYPE_INT_RGB );
    Graphics2D g2d = (Graphics2D) bimage2.getGraphics();

    if ( resized )
    {
      g2d.scale( (double) width / srcWidth, (double) height / srcHeight );
    }
    else
    {
      g2d.scale( scale, scale );
    }

    g2d.drawImage( bimage, 0, 0, new JFrame() );

    try
    {
      ImageIO.write( bimage2, format, new File( filename ) );
    }
    catch ( Exception ex )
    {
      throw new RuntimeException( ex );
    }
  }

  public void resize( int width, int height )
  {
    if ( width < 1 )
    {
      width = 1;
    }
    if ( height < 1 )
    {
      height = 1;
    }
    this.width = width;
    this.height = height;
    resized = true;
  }

  public boolean isResized()
  {
    return resized;
  }

  public double getScale()
  {
    if ( resized ){ return 0.0; }
    return scale;
  }

  public void setScale( double scale )
  {
    if ( scale < 0.01 )
    {
      scale = 0.01;
    }
    else if ( scale > 16.0 )
    {
      scale = 16.0;
    }
    this.scale = scale;
    height = (int) ( srcHeight * scale );
    width = (int) ( srcWidth * scale );
    resized = false;
  }

  public int getHeight()
  {
    return height;
  }

  public int getWidth()
  {
    return width;
  }

  public int getSrcHeight()
  {
    return srcHeight;
  }

  public int getSrcWidth()
  {
    return srcWidth;
  }

  public static void main( String args[] )
  {
    if ( args.length != 3 )
    {
      System.out.println( "Modo de usar: java ImageScale [origem] [destino] [escala]" );
      System.exit( 0 );
    }
    SImage image = new SImage( args[0], Double.parseDouble( args[2] ) );
    image.write( args[1], SImage.JPEG );
    image.setScale( 0.5 );
    image.write( "1" + args[1], SImage.PNG );
    image.resize( image.getWidth() + 300, image.getHeight() + 100 );
    image.write( "2" + args[1], SImage.JPEG );
  }
}