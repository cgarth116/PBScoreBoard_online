package ru.irbish.pbscoreboard.models;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;


public class SchedulePDFExporter {
    private List<Schedule> scheduleList;
    private String tournamentName;

    public SchedulePDFExporter(List<Schedule> scheduleList, String tournamentName) {
        this.scheduleList = scheduleList;
        this.tournamentName = tournamentName;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(3);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("N", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Team A", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Team B", font));
        table.addCell(cell);

    }

    private void writeTableData(PdfPTable table) {
        for (Schedule schedule : scheduleList) {
            Long idMatch = schedule.getIdMatch();
            if (idMatch != null) {
                table.addCell(String.valueOf(idMatch));
                table.addCell(schedule.getTeamA());
                table.addCell(schedule.getTeamB());
            }
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, response.getOutputStream());

            document.open();
            Font font = FontFactory.getFont(FontFactory.defaultEncoding);
            font.setSize(18);
            font.setColor(Color.BLUE);

            Paragraph p = new Paragraph("Расписание для", font);
            p.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(p);

            p = new Paragraph(tournamentName, font);
            p.setAlignment(Paragraph.ALIGN_CENTER);

            document.add(p);

            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(100f);
            table.setWidths(new float[]{1.5f, 3.5f, 3.0f});
            table.setSpacingBefore(10);

            writeTableHeader(table);
            writeTableData(table);

            document.add(table);

            document.close();
    }
}
