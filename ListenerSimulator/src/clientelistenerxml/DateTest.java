/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package clientelistenerxml;

import java.text.SimpleDateFormat;
import java.text.ParseException;

public class DateTest {

  public boolean isValidDate(String inDate) {

    if (inDate == null)
      return false;

    //set the format to use as a constructor argument
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    if (inDate.trim().length() != dateFormat.toPattern().length())
      return false;

    dateFormat.setLenient(false);

    try {
      //parse the inDate parameter
      dateFormat.parse(inDate.trim());
    }
    catch (ParseException pe) {
      return false;
    }
    return true;
  }

  public static void main(String[] args) {

    DateTest test = new DateTest();

    System.out.println(test.isValidDate(null));
    System.out.println(test.isValidDate("2005-02-29"));
  }
}

