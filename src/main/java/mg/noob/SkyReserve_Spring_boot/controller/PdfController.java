package mg.noob.SkyReserve_Spring_boot.controller;

import com.itextpdf.text.DocumentException;
import mg.noob.SkyReserve_Spring_boot.service.PdfGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pdf")
public class PdfController {

    @Autowired
    private PdfGenerationService pdfGenerationService;

    /**
     * Endpoint pour générer et télécharger le PDF d'une réservation
     * 
     * @param reservationId ID de la réservation
     * @return Le fichier PDF à télécharger ou une erreur si la réservation n'existe
     *         pas
     */
    @GetMapping("/reservation/{reservationId}")
    public ResponseEntity<byte[]> generateReservationPdf(@PathVariable Integer reservationId) {
        try {
            byte[] pdfContent = pdfGenerationService.generateReservationPdf(reservationId);

            if (pdfContent == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("filename", "reservation-" + reservationId + ".pdf");
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

            return new ResponseEntity<>(pdfContent, headers, HttpStatus.OK);
        } catch (DocumentException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}