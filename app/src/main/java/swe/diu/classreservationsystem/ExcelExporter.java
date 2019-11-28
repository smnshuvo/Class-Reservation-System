package swe.diu.classreservationsystem;

import android.util.Log;

import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/*
* COPYRIGHT SMNSHUVO
* THIS IS A SUB PROJECT
* UNDER THE MAIN PROJECT
* CLASS RESERVATION SYSTEM
* THIS PROJECT IS USED TO
* GET EMPTY ROOMS FROM THE
* EXCEL FILE, FOLLOWING MY PYTHON PROJECT
* WHICH I MYSELF DON'T UNDERSTAND NOW.
* WHILE CODING THAT PROJECT ONLY GOD
* AND I KNEW WHAT I WAS CODING,
* NOW MONTHS AFTER COMPLETING THAT
* PROJECT ONLY GOD KNOWS
* WHAT I HAVE HAD CODED
* xD
 */
public class ExcelExporter {
    public static final String ROUTINE_XL = "G://xl.project/src/main/files/routine.xlsx";
    private static int startingCell, sundayStartRow,
                                mondayStartRow , tuesdayStartRow,
                                    wednesdayStartRow, thursdayStartRow;


    public final String TAG = "Excel : ";
    public ExcelExporter(String file) {
        Log.i(TAG, "ExcelExporter: CONSTRUCTOR");
        Sheet routine_sheet=null; // declared it outside of block to access it in a function

        try{
            FileInputStream routine_excel = new FileInputStream(file);
            Workbook workbook = new XSSFWorkbook(routine_excel); // Workbook is excel sheet holder
            routine_sheet = workbook.getSheetAt(0); // get the first sheet
            Iterator<Row> cellIterator = routine_sheet.iterator();

            Log.i(TAG, "ExcelExporter: FILE FOUND AND IS OK");

            while (cellIterator.hasNext()){

                Row currentRow = cellIterator.next();
                Iterator <Cell> iterator = currentRow.iterator();
                    while (iterator.hasNext()){
                        Cell currentCell = iterator.next();

                        if ( currentCell.getStringCellValue().toUpperCase().contains("SATURDAY")) { // if that cell has the value Saturday
                            // If you get saturday you must find sunday to know your limit
                            startingCell = currentCell.getRowIndex();
                            /* This +1 is added as the row it finds the text Saturday
                             * The day actually starts from the next row
                             * NO NEED !!!!
                             */
                            System.err.println(startingCell);
                            // everything above sunday is your limit
                        }

                        if (currentCell.getStringCellValue().toUpperCase().contains("SUNDAY")){
                            sundayStartRow = currentCell.getRowIndex()+1;
                            System.out.println(sundayStartRow);
                        }

                        if (currentCell.getStringCellValue().toUpperCase().contains("MONDAY")){
                            mondayStartRow = currentCell.getRowIndex()+1;
                            System.out.println(mondayStartRow);
                        }
                        if (currentCell.getStringCellValue().toUpperCase().contains("TUESDAY")){
                            tuesdayStartRow = currentCell.getRowIndex()+1;
                            System.out.println(tuesdayStartRow);
                        }
                        if (currentCell.getStringCellValue().toUpperCase().contains("WEDNESDAY")){
                            wednesdayStartRow = currentCell.getRowIndex()+1;
                            System.out.println(wednesdayStartRow);
                        }
                        if (currentCell.getStringCellValue().toUpperCase().contains("THURSDAY")){
                            thursdayStartRow = currentCell.getRowIndex();
                            System.out.println(thursdayStartRow);
                        }


                    }
                System.out.println("");

            }
        } catch (Exception e){
            Log.i(TAG, "ExcelExporter: FILE NOT FOUND");
        } finally {
            ArrayList<EmptyRoom> saturday = getEmptyRooms(routine_sheet, startingCell, sundayStartRow, "Saturday");
            ArrayList<EmptyRoom> sunday = getEmptyRooms(routine_sheet, sundayStartRow, mondayStartRow, "Sunday");
            ArrayList<EmptyRoom> monday = getEmptyRooms(routine_sheet, mondayStartRow, tuesdayStartRow, "Monday");
            ArrayList<EmptyRoom> tuesday = getEmptyRooms(routine_sheet, tuesdayStartRow, wednesdayStartRow, "Tuesday");
            ArrayList<EmptyRoom> wednesday = getEmptyRooms(routine_sheet, wednesdayStartRow, thursdayStartRow, "Wednesday");
            ArrayList<EmptyRoom> thursday = getEmptyRooms(routine_sheet, thursdayStartRow, 102, "Thursday");
                                    // getLastRow is producing NULL pointer exception
                                    // idk why
            CompleteRoutine completeRoutine = new CompleteRoutine(saturday, sunday, monday, tuesday, wednesday, thursday);
            completeRoutine.printSaturday();
            completeRoutine.pushRoutine();




        }


    }

    // TO DO: NEED A FUNCTION TO GET THE STARTING AND ENDING POINT

    public static ArrayList<EmptyRoom> getEmptyRooms(Sheet routine, int startingCell, int endingCell, String dayOfWeek){
        ArrayList<String> emptyRooms = new ArrayList<String>();

//            Iterator<Row> RowIterator = routine.iterator(); // iterate my row

            ArrayList<String> emptyRoomOfp1= new ArrayList<String>();
            ArrayList<String> emptyRoomOfp2= new ArrayList<String>();
            ArrayList<String> emptyRoomOfp3= new ArrayList<String>();
            ArrayList<String> emptyRoomOfp4= new ArrayList<String>();
            ArrayList<String> emptyRoomOfp5= new ArrayList<String>();
            ArrayList<String> emptyRoomOfp6= new ArrayList<String>();

            for (int row = startingCell; row < endingCell ; row++) {
                //currentRow = RowIterator.next(); // get a row by moving the iterator to next index
                Row currentRow = routine.getRow(row);

                Cell currentCourseCell_p1 = currentRow.getCell(1); // get a cell by pointing the cell index of a row
                Cell currentRoomCell_p1 = currentRow.getCell(0);

                Cell currentCourseCell_p2 = currentRow.getCell(4); // get a cell by pointing the cell index of a row
                Cell currentRoomCell_p2 = currentRow.getCell(3);

                Cell currentCourseCell_p3 = currentRow.getCell(7); // get a cell by pointing the cell index of a row
                Cell currentRoomCell_p3 = currentRow.getCell(6);

                Cell currentCourseCell_p4 = currentRow.getCell(10); // get a cell by pointing the cell index of a row
                Cell currentRoomCell_p4 = currentRow.getCell(9);

                Cell currentCourseCell_p5 = currentRow.getCell(13); // get a cell by pointing the cell index of a row
                Cell currentRoomCell_p5 = currentRow.getCell(12);

                Cell currentCourseCell_p6 = currentRow.getCell(16); // get a cell by pointing the cell index of a row
                Cell currentRoomCell_p6 = currentRow.getCell(15);

                try {
                    if (currentCourseCell_p1 != null && currentRoomCell_p1 != null) { // to fixx the
                        if (currentCourseCell_p1.getStringCellValue().isEmpty() && Character.isDigit(currentRoomCell_p1.getStringCellValue().charAt(0))) { // these are the empty rooms
                    /* Character.isDigit(currentCourseCell.getStringCellValue().charAt(0)))
                    is a double checking, we are being sure as the room number starts with a digit
                     */

                            Log.i("Period ","p1: "+currentRoomCell_p1.getStringCellValue()); // print the element of current row
                            emptyRoomOfp1.add(currentRoomCell_p1.getStringCellValue());
                        }
                    }

                } catch (NullPointerException e){
                    System.err.println("NULL POINTER " + e.toString());
                    System.err.println(currentRoomCell_p1.getStringCellValue());
                } catch (StringIndexOutOfBoundsException e){
                    System.out.println("STRING ERROR "+ e.toString());
                }

                // period 2
                try {

                        if (currentCourseCell_p2.getStringCellValue().isEmpty() && Character.isDigit(currentRoomCell_p2.getStringCellValue().charAt(0))) { // these are the empty rooms
                    /* Character.isDigit(currentCourseCell.getStringCellValue().charAt(0)))
                    is a double checking, we are being sure as the room number starts with a digit
                     */

                            System.out.println("p2: "+currentRoomCell_p2.getStringCellValue()); // print the element of current row
                            emptyRoomOfp2.add(currentRoomCell_p2.getStringCellValue());
                        }


                } catch (NullPointerException e){
                    System.err.println("NULL POINTER " + e.toString());
                    System.err.println("p2: " +currentRoomCell_p2.getStringCellValue());
                    // I GUESS THERE's ONE MORE WORK TO DO
                    // IF A CELL IS NULL I SHOULD CROSS CHECK THE PREVIOUS CELL
                    // IF IT CONTAINS ROOM NUMBER
                    emptyRoomOfp2.add(currentRoomCell_p2.getStringCellValue() + " (!)");
                } catch (StringIndexOutOfBoundsException e){
                    System.out.println("STRING ERROR "+ e.toString());

                }

                //>>> PERIOD 3
                try {

                        if (currentCourseCell_p3.getStringCellValue().isEmpty() && Character.isDigit(currentRoomCell_p3.getStringCellValue().charAt(0))) { // these are the empty rooms
                    /* Character.isDigit(currentCourseCell.getStringCellValue().charAt(0)))
                    is a double checking, we are being sure as the room number starts with a digit
                     */

                            System.out.println("p3: "+currentRoomCell_p3.getStringCellValue()); // print the element of current row
                            emptyRoomOfp3.add(currentRoomCell_p3.getStringCellValue());
                        }


                } catch (NullPointerException e){
                    System.err.println("NULL POINTER " + e.toString());
                    System.err.println("p3: " +currentRoomCell_p3.getStringCellValue());
                    emptyRoomOfp3.add(currentRoomCell_p3.getStringCellValue() + " (!)");
                } catch (StringIndexOutOfBoundsException e){
                    System.out.println("STRING ERROR "+ e.toString());
                }

                // Period 4 ==>>
                try {

                        if (currentCourseCell_p4.getStringCellValue().isEmpty() && Character.isDigit(currentRoomCell_p4.getStringCellValue().charAt(0))) { // these are the empty rooms
                    /* Character.isDigit(currentCourseCell.getStringCellValue().charAt(0)))
                    is a double checking, we are being sure as the room number starts with a digit
                     */

                            System.out.println("p4: "+currentRoomCell_p4.getStringCellValue()); // print the element of current row
                            emptyRoomOfp4.add(currentRoomCell_p4.getStringCellValue());
                        }


                } catch (NullPointerException e){
                    System.err.println("NULL POINTER " + e.toString());
                    System.err.println("p4: " +currentRoomCell_p3.getStringCellValue());
                    emptyRoomOfp4.add(currentRoomCell_p4.getStringCellValue() + " (!)");
                } catch (StringIndexOutOfBoundsException e){
                    System.out.println("STRING ERROR "+ e.toString());
                }


                // PERIOD 5 ==>>
                try {

                    if (currentCourseCell_p5.getStringCellValue().isEmpty() && Character.isDigit(currentRoomCell_p5.getStringCellValue().charAt(0))) { // these are the empty rooms
                    /* Character.isDigit(currentCourseCell.getStringCellValue().charAt(0)))
                    is a double checking, we are being sure as the room number starts with a digit
                     */

                        System.out.println("p5: "+currentRoomCell_p5.getStringCellValue()); // print the element of current row
                        emptyRoomOfp5.add(currentRoomCell_p5.getStringCellValue());
                    }


                } catch (NullPointerException e){
                    System.err.println("NULL POINTER " + e.toString());
                    System.err.println("p5: " +currentRoomCell_p5.getStringCellValue());
                    emptyRoomOfp5.add(currentRoomCell_p5.getStringCellValue() + " (!)");
                } catch (StringIndexOutOfBoundsException e){
                    System.out.println("STRING ERROR "+ e.toString());
                }


                // period 6

                try {

                    if (currentCourseCell_p6.getStringCellValue().isEmpty() && Character.isDigit(currentRoomCell_p6.getStringCellValue().charAt(0))) { // these are the empty rooms
                    /* Character.isDigit(currentCourseCell.getStringCellValue().charAt(0)))
                    is a double checking, we are being sure as the room number starts with a digit
                     */

                        System.out.println("p6: "+currentRoomCell_p6.getStringCellValue()); // print the element of current row
                        emptyRoomOfp6.add(currentRoomCell_p6.getStringCellValue());
                    }


                } catch (NullPointerException e){
                    System.err.println("NULL POINTER " + e.toString());
                    System.err.println("p6: " +currentRoomCell_p6.getStringCellValue());
                    emptyRoomOfp6.add(currentRoomCell_p6.getStringCellValue() + " (!)");
                } catch (StringIndexOutOfBoundsException e){
                    System.out.println("STRING ERROR "+ e.toString());
                }





            }

            EmptyRoom p1 = new EmptyRoom(dayOfWeek,"1", emptyRoomOfp1);
            EmptyRoom p2 = new EmptyRoom(dayOfWeek,"2", emptyRoomOfp2);
            EmptyRoom p3 = new EmptyRoom(dayOfWeek,"3", emptyRoomOfp3);
            EmptyRoom p4 = new EmptyRoom(dayOfWeek,"4", emptyRoomOfp4);
            EmptyRoom p5 = new EmptyRoom(dayOfWeek,"5", emptyRoomOfp5);
            EmptyRoom p6 = new EmptyRoom(dayOfWeek,"6", emptyRoomOfp6);

            ArrayList<EmptyRoom> routineOfADay = new ArrayList<EmptyRoom>();

            routineOfADay.add(p1);
            routineOfADay.add(p2);
            routineOfADay.add(p3);
            routineOfADay.add(p4);
            routineOfADay.add(p5);
            routineOfADay.add(p6);


        return routineOfADay;

    }
}
