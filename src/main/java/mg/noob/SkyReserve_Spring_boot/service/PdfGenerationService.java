package mg.noob.SkyReserve_Spring_boot.service;

import mg.noob.SkyReserve_Spring_boot.model.Reservation;
import mg.noob.SkyReserve_Spring_boot.repository.ReservationRepository;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class PdfGenerationService {

    @Autowired
    private ReservationRepository reservationRepository;

    private static final Font TITLE_FONT = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.DARK_GRAY);
    private static final Font SUBTITLE_FONT = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.DARK_GRAY);
    private static final Font NORMAL_FONT = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.BLACK);
    private static final Font HEADER_FONT = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.WHITE);

    /**
     * Génère un PDF pour une réservation spécifiée par son ID
     * 
     * @param reservationId ID de la réservation
     * @return Le contenu du PDF en tableau d'octets ou null si la réservation
     *         n'existe pas
     * @throws DocumentException en cas d'erreur lors de la génération du PDF
     */
    public byte[] generateReservationPdf(Integer reservationId) throws DocumentException {
        Optional<Reservation> optionalReservation = reservationRepository.findById(reservationId);

        if (optionalReservation.isEmpty()) {
            return null;
        }

        Reservation reservation = optionalReservation.get();

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, baos);

            document.open();
            addDocumentContent(document, reservation);
            document.close();

            return baos.toByteArray();
        } catch (IOException e) {
            throw new DocumentException("Erreur lors de la génération du PDF: " + e.getMessage());
        }
    }

    private void addDocumentContent(Document document, Reservation reservation) throws DocumentException {
        // Titre du document
        Paragraph title = new Paragraph("Confirmation de Réservation", TITLE_FONT);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(20);
        document.add(title);

        // Informations sur la réservation
        document.add(new Paragraph("Détails de la Réservation", SUBTITLE_FONT));
        document.add(new Paragraph("Numéro de réservation: " + reservation.getId(), NORMAL_FONT));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String reservationDate = reservation.getReservationDate()
                .atZone(ZoneId.systemDefault())
                .format(formatter);
        document.add(new Paragraph("Date de réservation: " + reservationDate, NORMAL_FONT));
        document.add(new Paragraph("Nombre de sièges: " + reservation.getSeatsNumber(), NORMAL_FONT));
        document.add(new Paragraph("Nombre de sièges enfants: " + reservation.getSeatsNumberChildren(), NORMAL_FONT));
        document.add(new Paragraph(
                "Statut de la réservation: " + reservation.getReservationStatus().getReseravtionName(), NORMAL_FONT));
        document.add(new Paragraph("Type de siège: " + reservation.getSeatType().getTypeName(), NORMAL_FONT));

        if (reservation.getHasPromotion()) {
            document.add(new Paragraph("Promotion appliquée: Oui", NORMAL_FONT));
        }

        document.add(Chunk.NEWLINE);

        // Informations sur le vol
        document.add(new Paragraph("Détails du Vol", SUBTITLE_FONT));
        String departureDate = reservation.getFlight().getDepartureDate()
                .atZone(ZoneId.systemDefault())
                .format(formatter);
        String arrivalDate = reservation.getFlight().getArrivalDate()
                .atZone(ZoneId.systemDefault())
                .format(formatter);
        document.add(new Paragraph("ID du vol: " + reservation.getFlight().getId(), NORMAL_FONT));
        document.add(new Paragraph("Date de départ: " + departureDate, NORMAL_FONT));
        document.add(new Paragraph("Date d'arrivée: " + arrivalDate, NORMAL_FONT));

        document.add(Chunk.NEWLINE);

        // Informations sur le passager
        document.add(new Paragraph("Informations du Passager", SUBTITLE_FONT));
        document.add(new Paragraph("Nom: " + reservation.getUser().getName(), NORMAL_FONT));

        document.add(Chunk.NEWLINE);

        // Création d'un tableau récapitulatif
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);

        // En-têtes du tableau
        PdfPCell headerCell1 = new PdfPCell(new Phrase("Description", HEADER_FONT));
        headerCell1.setBackgroundColor(BaseColor.DARK_GRAY);
        headerCell1.setPadding(5);
        table.addCell(headerCell1);

        PdfPCell headerCell2 = new PdfPCell(new Phrase("Détails", HEADER_FONT));
        headerCell2.setBackgroundColor(BaseColor.DARK_GRAY);
        headerCell2.setPadding(5);
        table.addCell(headerCell2);

        // Ajouter les lignes au tableau
        addTableRow(table, "Numéro de réservation", String.valueOf(reservation.getId()));
        addTableRow(table, "Date de réservation", reservationDate);
        addTableRow(table, "Type de siège", reservation.getSeatType().getTypeName());
        addTableRow(table, "Statut", reservation.getReservationStatus().getReseravtionName());
        addTableRow(table, "Nombre de sièges", String.valueOf(reservation.getSeatsNumber()));
        addTableRow(table, "Nombre de sièges enfants", String.valueOf(reservation.getSeatsNumberChildren()));
        addTableRow(table, "Date de départ", departureDate);
        addTableRow(table, "Date d'arrivée", arrivalDate);
        addTableRow(table, "Passager", reservation.getUser().getName());

        document.add(table);

        // Pied de page
        document.add(Chunk.NEWLINE);
        Paragraph footer = new Paragraph("Merci d'avoir choisi SkyReserve pour votre voyage!", NORMAL_FONT);
        footer.setAlignment(Element.ALIGN_CENTER);
        document.add(footer);
    }

    private void addTableRow(PdfPTable table, String label, String value) {
        PdfPCell cell1 = new PdfPCell(new Phrase(label, NORMAL_FONT));
        cell1.setPadding(5);
        table.addCell(cell1);

        PdfPCell cell2 = new PdfPCell(new Phrase(value, NORMAL_FONT));
        cell2.setPadding(5);
        table.addCell(cell2);
    }
}