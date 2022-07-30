/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nivers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
//import javax.swing.JOptionPane;
//import javax.xml.crypto.Data;

/**
 *
 * @author 8741417
 */
public class CalcDatas {

    public static String soma_data_dia(String data, int dias){
//        JOptionPane.showMessageDialog(null, "data = "+data+"\ndias = "+dias);
        int ano, mes, dia;
        ano = Integer.parseInt(data.substring(0, 4));
        mes = Integer.parseInt(data.substring(5, 7))-1;
        dia = Integer.parseInt(data.substring(8, 10));
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = new GregorianCalendar(ano, mes, dia);
        c.add(Calendar.DAY_OF_MONTH, dias);  // 60 dias depois
        data = sd.format(c.getTime());
        return data;
    }

    public static String hoje (){
        Date d = new Date();
//        d.setDate(d.getDate());
        SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd");
        String s = String.valueOf(formatador.format(d));
        return s;
    }

    public static String ano(){
        Date d = new Date();
        SimpleDateFormat formatador = new SimpleDateFormat("yyyy");
        String s = String.valueOf(formatador.format(d));
        return s;
    }

    public static String mes(){
        Date d = new Date();
        SimpleDateFormat formatador = new SimpleDateFormat("MM");
        String s = String.valueOf(formatador.format(d));
        return s;
    }

    public static String soma_now_dia(int dias){
        Date d = new Date();
        d.setDate(d.getDate() + dias);
        SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd");
        String s = String.valueOf(formatador.format(d));
//        s = s.substring(5);
        return s;
    }

    public Long compararDatas(String data1,String data2){
      SimpleDateFormat df = new SimpleDateFormat ("dd/MM/yyyy");
      Date d1 = null;
      Date d2 = null;
      try {
             d1 = df.parse (data1);
             d2 = df.parse (data2);
       } catch (java.text.ParseException evt ) {}

       long dt = (d2.getTime() - d1.getTime()) + 3600000;
       long dias = (dt / 86400000L) + 1;

       return dias;
     }
/*
    static int dataDiff(Date dataLow, Date dataHigh){

      GregorianCalendar startTime = new GregorianCalendar();
      GregorianCalendar endTime = new GregorianCalendar();
      GregorianCalendar curTime = new GregorianCalendar();
      GregorianCalendar baseTime = new GregorianCalendar();
      startTime.setTime(dataLow);
      endTime.setTime(dataHigh);
      int dif_multiplier = 1;
      // Verifica a ordem de inicio das datas
      if( dataLow.compareTo( dataHigh ) < 0 ){
          baseTime.setTime(dataHigh);
          curTime.setTime(dataLow);
          dif_multiplier = 1;
      }else{
          baseTime.setTime(dataLow);
          curTime.setTime(dataHigh);
          dif_multiplier = -1;
      }
      int result_years = 0;
      int result_months = 0;
      int result_days = 0;
      // Para cada mes e ano, vai de mes em mes pegar o ultimo dia para import acumulando
      // no total de dias. Ja leva em consideracao ano bissesto
      while( curTime.get(GregorianCalendar.YEAR) < baseTime.get(GregorianCalendar.YEAR) ||
             curTime.get(GregorianCalendar.MONTH) < baseTime.get(GregorianCalendar.MONTH)  )
      {

          int max_day = curTime.getActualMaximum( GregorianCalendar.DAY_OF_MONTH );
          result_months += max_day;
          curTime.add(GregorianCalendar.MONTH, 1);

      }
      // Marca que é um saldo negativo ou positivo
      result_months = result_months*dif_multiplier;
      // Retirna a diferenca de dias do total dos meses
      result_days += (endTime.get(GregorianCalendar.DAY_OF_MONTH) - startTime.get(GregorianCalendar.DAY_OF_MONTH));
      return result_years+result_months+result_days;
 }


    public static String dif_datas01(Date d1, Date d2){ //d2 sempre é 31 de dezembro, então não precisa ser passado
        Date d = new Date();
        SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd");
    return "";
    }

    public static void diferenca_datas(String[] args) {
         Calendar c1 = new GregorianCalendar(2004, 10, 6);
         Calendar c2 = new GregorianCalendar(2004, 10, 20);
         Date d1 = c1.getTime();
         Date d2 = c2.getTime();
         double diffDias = Math.floor((d2.getTime() - d1.getTime()) / 1000.0 / 86400.00);
         System.out.println (diffDias);
     }
*/

}
