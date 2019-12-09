package es.mpr.plataformamensajeria.web.export;

import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.jsp.JspException;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.displaytag.Messages;
import org.displaytag.exception.BaseNestableJspTagException;
import org.displaytag.exception.SeverityEnum;
import org.displaytag.export.BinaryExportView;
import org.displaytag.model.Column;
import org.displaytag.model.ColumnIterator;
import org.displaytag.model.HeaderCell;
import org.displaytag.model.Row;
import org.displaytag.model.RowIterator;
import org.displaytag.model.TableModel;

/**
 * The Class PlataformaMensajeriaExcelExport.
 */
public class PlataformaMensajeriaExcelExport implements BinaryExportView{
	    protected static final String DEPRECATION = "deprecation";

		/**
	     * TableModel to render.
	     */
	    private TableModel model;

	    /** export full list?. */
	    private boolean exportFull;

	    /** include header in export?. */
	    private boolean header;

	    /** decorate export?. */
	    private boolean decorated;

	    /**
	     * Generated sheet.
	     */
	    private HSSFSheet sheet;

	    /**
    	 * Modificar parameters.
    	 *
    	 * @param tableModel the table model
    	 * @param exportFullList the export full list
    	 * @param includeHeader the include header
    	 * @param decorateValues the decorate values
    	 * @see org.displaytag.export.ExportView#setParameters(TableModel, boolean, boolean, boolean)
    	 */
	    public void setParameters(TableModel tableModel, boolean exportFullList, boolean includeHeader,
	        boolean decorateValues) {
	        this.model = tableModel;
	        this.exportFull = exportFullList;
	        this.header = includeHeader;
	        this.decorated = decorateValues;
	    }

	    /**
    	 * Obtener mime type.
    	 *
    	 * @return "application/vnd.ms-excel"
    	 * @see org.displaytag.export.BaseExportView#getMimeType()
    	 */
	    public String getMimeType() {
	        return "application/vnd.ms-excel"; 
	        //$NON-NLS-1$
	    }

	    /**
    	 * Do export.
    	 *
    	 * @param out the out
    	 * @throws JspException the jsp exception
    	 * @see org.displaytag.export.BinaryExportView#doExport(OutputStream)
    	 */
	    public void doExport(OutputStream out) throws JspException {
	        try {
	            HSSFWorkbook wb = new HSSFWorkbook();
	            sheet = wb.createSheet("-");
	            int rowNum = 0;
	            int colNum = 0;

	            if (this.header) {
	                // Create an header row
	                HSSFRow xlsRow = sheet.createRow(rowNum++);

	                HSSFCellStyle headerStyle = wb.createCellStyle();
	                headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	                
	                HSSFPalette palette = wb.getCustomPalette();
	                palette.setColorAtIndex(HSSFColor.BLUE.index,
	                        (byte) 214,  
	                        //RGB red (0-255)
	                        (byte) 220,    
	                        //RGB green
	                        (byte) 223     
	                        //RGB blue
	                );
	                headerStyle.setFillBackgroundColor(HSSFColor.BLUE.index);
	                headerStyle.setFillForegroundColor(HSSFColor.BLUE.index);
	                headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	                HSSFFont bold = wb.createFont();
	                bold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	                bold.setColor(HSSFColor.BLACK.index);
	                headerStyle.setFont(bold);

	                Iterator<?> iterator = this.model.getHeaderCellList().iterator();

	                while (iterator.hasNext()) {
	                    HeaderCell headerCell = (HeaderCell) iterator.next();

	                    String columnHeader = headerCell.getTitle();

	                    if (columnHeader == null) {
	                        columnHeader = StringUtils.capitalize(headerCell.getBeanPropertyName());
	                    }

	                    @SuppressWarnings(DEPRECATION)
						HSSFCell cell = xlsRow.createCell((short) colNum++);
	                    cell.setCellValue(StringEscapeUtils.unescapeHtml(StringUtils.capitalize(columnHeader)));
	                    cell.setCellStyle(headerStyle);
	                }
	            }

	            // get the correct iterator (full or partial list according to the exportFull field)
	            RowIterator rowIterator = this.model.getRowIterator(this.exportFull);
	            // iterator on rows
	            while (rowIterator.hasNext()) {
	                Row row = rowIterator.next();
	                HSSFRow xlsRow = sheet.createRow(rowNum++);
	                colNum = 0;

	                // iterator on columns
	                ColumnIterator columnIterator = row.getColumnIterator(this.model.getHeaderCellList());
	                while (columnIterator.hasNext()) {
	                    Column column = columnIterator.nextColumn();
	                    // Get the value to be displayed for the column
	                    Object value = column.getValue(this.decorated);

	                    @SuppressWarnings(DEPRECATION)
						HSSFCell cell = xlsRow.createCell((short) colNum++);

	                    if (value instanceof Number) {
	                        Number num = (Number) value;
	                        cell.setCellValue(num.doubleValue());
	                    } else if (value instanceof Date) {
	                        cell.setCellValue((Date) value);
	                    } else if (value instanceof Calendar) {
	                        cell.setCellValue((Calendar) value);
	                    } else {
	                    	String returnString = ObjectUtils.toString(value);
	                    	if(returnString.indexOf("<span class='activo'>")!=-1){
	                    		returnString = "Si";
	                    	}else if(returnString.indexOf("<span class='inactivo'>")!=-1){
	                    		returnString = "No";
	                    	}else if(returnString.indexOf("<center>")!=-1){
	                    		returnString = "";
	                    	}
	                        cell.setCellValue(escapeColumnValue(returnString));
	                    }
	                }
	            }
	            wb.write(out);
	        } catch (Exception e) {
	            throw new ExcelGenerationException(e);
	        }
	    }

	    // patch from Karsten Voges
	    /**
	     * Escape certain values that are not permitted in excel cells.
	     * @param rawValue the object value
	     * @return the escaped value
	     */
	    protected String escapeColumnValue(Object rawValue) {
	        if (rawValue == null) {
	            return null;
	        }
	        String returnString = ObjectUtils.toString(rawValue);
	        // escape the String to get the tabs, returns, newline explicit as \t \r \n
	        returnString = StringEscapeUtils.escapeJava(StringUtils.trimToEmpty(returnString));
	        // remove tabs, insert four whitespaces instead
	        returnString = StringUtils.replace(StringUtils.trim(returnString), "\\t", "    ");
	        // Eliminados los caracteres extranos
	        returnString = StringEscapeUtils.unescapeHtml(returnString);
	        // remove the return, only newline valid in excel
	        returnString = StringUtils.replace(StringUtils.trim(returnString), "\\r", " ");
	        return StringEscapeUtils.unescapeJava(returnString);
	    }

	    /**
	     * Wraps IText-generated exceptions.
	     * @author Fabrizio Giustina
	     * @version $Revision: 1.2 $ ($Author: fgiust $)
	     */
	    static class ExcelGenerationException extends BaseNestableJspTagException
	    {

	        /**
	         * D1597A17A6.
	         */
	        private static final long serialVersionUID = 899149338534L;

	        /**
	         * Instantiate a new PdfGenerationException with a fixed message and the given cause.
	         * @param cause Previous exception
	         */
	        public ExcelGenerationException(Throwable cause) {
	            super(PlataformaMensajeriaExcelExport.class, Messages.getString("ExcelView.errorexporting"), cause); 
	            //$NON-NLS-1$
	        }

	        /**
        	 * Obtener severity.
        	 *
        	 * @return severity
        	 * @see org.displaytag.exception.BaseNestableJspTagException#getSeverity()
        	 */
	        public SeverityEnum getSeverity() {
	            return SeverityEnum.ERROR;
	        }
	    }
}
