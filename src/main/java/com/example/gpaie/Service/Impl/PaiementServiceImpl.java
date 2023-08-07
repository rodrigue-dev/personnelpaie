package com.example.gpaie.Service.Impl;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.gpaie.Entity.Paiement;
import com.example.gpaie.Entity.User;
import com.example.gpaie.Model.EmailDetails;
import com.example.gpaie.Model.PaiementModel;
import com.example.gpaie.Repository.HeureSupplRepository;
import com.example.gpaie.Repository.PaiementRepository;
import com.example.gpaie.Repository.UserRepository;
import com.example.gpaie.Service.FichePresenceService;
import com.example.gpaie.Service.PaiementService;
import com.example.gpaie.Service.PlaningService;
import com.example.gpaie.Utils.PdfUtil;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class PaiementServiceImpl implements PaiementService {
    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private PaiementRepository paiementRepository;
    @Autowired
    private HeureSupplRepository heureSupplRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FichePresenceService fichePaieService;
    @Autowired
    private PlaningService planingService;
    @Value("${files.path}")
    private String filesPath;
    @Value("${datepaie}")
    private int daypaie;
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public PaiementModel save(PaiementModel paiementModel) {
        Paiement paiement;
        paiement = paiementRepository.findById(paiementModel.getId()).get();
        if (paiement == null) {
            
            return paiementModel;
        }
        //System.out.println(paiementModel.getPrecomptePro());
        paiement.setCnss_retenue(paiementModel.getCnss_retenue());
        paiement.setCotisationCnss(paiementModel.getCotisationCnss());
        paiement.setPrecomptePro(paiementModel.getPrecomptePro());
        paiement.setPrime_HS(paiementModel.getPrime_HS());
        paiement.setPrime_equipe(paiementModel.getPrime_equipe());
        paiement.setPrime_prestation(paiementModel.getPrime_prestation());
        paiement.setSuppl_transport(paiementModel.getSuppl_transport());
        paiement.setTotal_prime(
                paiementModel.getPrime_prestation() + paiementModel.getPrime_HS() + paiementModel.getPrime_equipe());
        paiement.setRetenu_chomage(paiementModel.getRetenu_chomage());
        paiement.setRetenu_retraite(paiementModel.getRetenu_chomage());
        paiement.setRetenu_total(paiementModel.getRetenu_retraite() + paiementModel.getRetenu_chomage()
                + paiementModel.getCnss_retenue());
        paiementRepository.saveAndFlush(paiement);
        return paiementModel;
    }

    @Override
    public Optional<PaiementModel> partialUpdate(PaiementModel paiementModel) {
        return Optional.of(paiementModel);
    }

    @Override
    public List<PaiementModel> findAll() {
        return paiementRepository.findAll().stream().filter(e->e.getMonth()==8).map(this::paiementToPaiementModel).collect(Collectors.toList());
    }
    @Override
    public List<PaiementModel> findAll(int month) {
        return paiementRepository.findAll().stream().filter(e->e.getMonth()==month).map(this::paiementToPaiementModel).collect(Collectors.toList());
    }
    @Override
    public Optional<PaiementModel> findOne(Long id) {
        return paiementRepository.findById(id).map(this::paiementToPaiementModel);
    }

    @Override
    public void delete(Long id) {
        paiementRepository.deleteById(id);
        ;
    }

    public PaiementModel paiementToPaiementModel(Paiement paiement) {
        return new PaiementModel(paiement);
    }

    public void saveInfoPaiement(long user_id, int month, int year) {
        User user = userRepository.findById(user_id).get();
    }

    @Override
    public List<PaiementModel> calculSalaire(int previopusmonth, int year,Long id_user) {
        if(LocalDate.now().getDayOfMonth() != daypaie){
            System.out.println(LocalDate.now().getDayOfMonth());
            throw new UsernameNotFoundException("Could not calcul salaire in date = " + LocalDate.now().getDayOfMonth());
        }
        var month=previopusmonth;
/*         if(month==1){
            month=12;
        }else{
            month=previopusmonth-1;
        } */
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        var date_debut = LocalDate.of(year, month, 01);
        var date_fin = LocalDate.of(year, month, YearMonth.of(year, month).atEndOfMonth().getDayOfMonth());
        var employes = userRepository.findAll().stream().filter(e->e.isEnabled())
        .filter(f->f.getId() !=id_user)
        .collect(Collectors.toList());
        List<PaiementModel> paiementModels = new ArrayList<>();
        for (User user : employes) {
            var paie = paiementRepository.findOneByMonthAndYearAndUser(month, year, user);
            if (paie != null) {
                paiementRepository.delete(paie);
                paiementRepository.flush();
            }
            if (paie == null) {
                paie = new Paiement();
                paie.setUser(user);
                paie.setCreatedAt(LocalDateTime.now());
                paie.setModifiedAt(LocalDateTime.now());
                paie.setMonth(month);
                paie.setYear(year);
            }
            var fichePaies = fichePaieService.findByEmployeBetwennDate(user.getId(),
                    date_debut.format(dateTimeFormatter), date_fin.format(dateTimeFormatter));
         /*    var heuresTrav = fichePaies.stream().filter(e -> e.getHeureDebut().length() > 2).map(e -> {
                var h_=(int)Duration.between(LocalTime.parse(e.getHeureDebut()) , LocalTime.parse(e.getHeureFin())).toHours();
               // return minEpocheTo(e.getHeureDebut().toString(), e.getHeureFin().toString());
               return h_;

            }).reduce(0, (x, y) -> x + y); */
            var heuresTrav = fichePaies.stream().filter(e -> e.getHeureDebut().length() > 2)
            .filter(e->LocalDate.parse(e.getDate_presence()).getDayOfWeek().getValue()<6).map(e -> {
                var h_=(int)Duration.between(LocalTime.parse(e.getHeureDebut()) , LocalTime.parse(e.getHeureFin())).toHours();
                return minEpocheTo(e.getHeureDebut().toString(), e.getHeureFin().toString());
              // return h_;

            }).mapToDouble(f->f.doubleValue()).sum();
            var heure_supplementaires = heureSupplRepository
                    .findAllByUserAndDateHeureSupplBetween(user, date_debut, date_fin)
                    .stream()
                    .map(e -> minEpocheTo(e.getHeureDebut().toString(), e.getHeureFin().toString()))
                    .mapToDouble(f->f.doubleValue())
                    .reduce(0, (x, y) -> x + y);

            var heure_pointe = (double) (heuresTrav/ 60);
            var heure_pointe_arr = Math.round(heure_pointe * 100.0) / 100.0;
            var total_jour_travaille = fichePaies.stream().filter(e -> e.getHeureDebut().isBlank() == false).count();
            System.out.println(heure_pointe);
            /* fichePaies.stream().filter(e -> e.getHeureDebut().isBlank() == false)
                    .forEach(e -> System.out.println(e.getHeureDebut())); */
            // System.out.println(total_jour_travaille);
            var prime_HS = heure_supplementaires/60 * user.getFonction().getSalaireHeure()*2;
            var prime_equipe = Math.round((heure_pointe_arr * user.getFonction().getSalaireHeure()* 0.25 )* 100.0) / 100.0;
            var transp = Math.round((total_jour_travaille * user.getFonction().getSalaireHeure()*0.62) * 100.0) / 100.0;
            var prime_repas = total_jour_travaille * 8;
            var retenu_chomage = 0;
            var retenu_retraitre = 0;
            var prestation = Math.round((heure_pointe_arr * user.getFonction().getSalaireHeure()) * 100.0) / 100.0;
            
            var total_prime = (Math.round((prime_equipe + prime_repas + transp + prime_HS) * 100.0)) / 100.0;
            var salaire_brut = (Math.round((prestation + total_prime) * 100.0) / 100.0);
            var cotisation_cnss = Math.round((salaire_brut * 0.0271)*100.0) / 100.0;
            var total_retenu = cotisation_cnss + retenu_chomage + retenu_retraitre;
            var salaire_net = Math.round((salaire_brut - total_retenu) * 100.0)/ 100.0;
            if(total_jour_travaille>0){
                paie.setPrime_HS(prime_HS);
                paie.setPrime_prestation(prestation);
                paie.setTotalHeureSuppl(heure_supplementaires/60);
                paie.setTotalJours((int) total_jour_travaille);
                paie.setPrime_equipe(prime_equipe);
                paie.setSuppl_transport(transp);
                paie.setTotal_heure(heure_pointe_arr);
                paie.setTotal_prime(total_prime);
                paie.setRetenu_chomage(retenu_chomage);
                paie.setRetenu_retraite(retenu_retraitre);
                paie.setRetenu_total(total_retenu);
                paie.setTotal_brut(salaire_brut);
                paie.setTotal_net(salaire_net);
                paie.setTotal_impossable(salaire_net);
                paie.setCotisationCnss(cotisation_cnss); 
            }else{
                paie.setPrime_HS(0);
                paie.setPrime_prestation(0);
                paie.setTotalHeureSuppl(0);
                paie.setTotalJours((int) 0);
                paie.setPrime_equipe(0);
                paie.setSuppl_transport(0);
                paie.setTotal_heure(0);
                paie.setTotal_prime(0);
                paie.setRetenu_chomage(0);
                paie.setRetenu_retraite(0);
                paie.setRetenu_total(0);
                paie.setTotal_brut(0);
                paie.setTotal_net(0);
                paie.setTotal_impossable(0);
                paie.setCotisationCnss(0);
            }


            paiementRepository.saveAndFlush(paie);
            //generatePdf(paie);
            var paiementModel = new PaiementModel(paie);
            paiementModels.add(paiementModel);
        }
        return paiementModels;
    }
    private void calculOnePaie(Paiement paiement){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        var date_debut = LocalDate.of(paiement.getYear(), paiement.getMonth(), 01);
        var date_fin = LocalDate.of(paiement.getYear(), paiement.getMonth(), YearMonth.of(paiement.getYear(), paiement.getMonth()).atEndOfMonth().getDayOfMonth());
        var paie = paiement;
            if (paie == null) {
                paie = new Paiement();
                paie.setUser(paiement.getUser());
                paie.setDatePaie(LocalDate.now());
                paie.setMonth(paiement.getMonth());
                paie.setYear(paiement.getYear());
            }
            var fichePaies = fichePaieService.findByEmployeBetwennDate(paiement.getUser().getId(),
                    date_debut.format(dateTimeFormatter), date_fin.format(dateTimeFormatter));
            var heuresTrav = fichePaies.stream().filter(e -> e.getHeureDebut().length() > 2).map(e -> {
                return minEpocheTo(e.getHeureDebut().toString(), e.getHeureFin().toString());
            }).reduce(0, (x, y) -> x + y);
            var heure_supplementaires = heureSupplRepository
                    .findAllByUserAndDateHeureSupplBetween(paiement.getUser(), date_debut, date_fin)
                    .stream()
                    .map(e -> minEpocheTo(e.getHeureDebut().toString(), e.getHeureFin().toString()))
                    .reduce(0, (x, y) -> x + y);

            var heure_pointe = (double) (heuresTrav.doubleValue() / 60);
            var heure_pointe_arr = Math.round(heure_pointe * 100.0) / 100.0;
            var total_jour_travaille = fichePaies.stream().filter(e -> e.getHeureDebut().isBlank() == false).count();
            fichePaies.stream().filter(e -> e.getHeureDebut().isBlank() == false)
                    .forEach(e -> System.out.println(e.getHeureDebut()));
            
            var prime_HS = heure_supplementaires/60 * 35;
            var prime_equipe = Math.round((heure_pointe_arr * 1.25 )* 100.0) / 100.0;
            var transp = Math.round((total_jour_travaille * 1.62) * 100.0) / 100.0;
            var prime_repas = total_jour_travaille * 8;
            var retenu_chomage = 0;
            var retenu_retraitre = 0;
            var prestation = Math.round((heure_pointe_arr * 17.5) * 100.0) / 100.0;
            
            var total_prime = (Math.round((prime_equipe + prime_repas + transp + prime_HS) * 100.0)) / 100.0;
            var salaire_brut = (Math.round((prestation + total_prime) * 100.0) / 100.0);
            var cotisation_cnss = Math.round((salaire_brut * 0.0271)*100.0) / 100.0;
            var total_retenu = cotisation_cnss + retenu_chomage + retenu_retraitre;
            var salaire_net = Math.round((salaire_brut - total_retenu) * 100.0)/ 100.0;
            if(total_jour_travaille>0){
                paie.setPrime_HS(prime_HS);
                paie.setPrime_prestation(prestation);
                paie.setTotalHeureSuppl(heure_supplementaires/60);
                paie.setTotalJours((int) total_jour_travaille);
                paie.setPrime_equipe(prime_equipe);
                paie.setSuppl_transport(transp);
                paie.setTotal_heure(heure_pointe_arr);
                paie.setTotal_prime(total_prime);
                paie.setRetenu_chomage(retenu_chomage);
                paie.setRetenu_retraite(retenu_retraitre);
                paie.setRetenu_total(total_retenu);
                paie.setTotal_brut(salaire_brut);
                paie.setTotal_net(salaire_net);
                paie.setTotal_impossable(salaire_net);
                paie.setCotisationCnss(cotisation_cnss); 
            }else{
                paie.setPrime_HS(0);
                paie.setPrime_prestation(0);
                paie.setTotalHeureSuppl(0);
                paie.setTotalJours((int) 0);
                paie.setPrime_equipe(0);
                paie.setSuppl_transport(0);
                paie.setTotal_heure(0);
                paie.setTotal_prime(0);
                paie.setRetenu_chomage(0);
                paie.setRetenu_retraite(0);
                paie.setRetenu_total(0);
                paie.setTotal_brut(0);
                paie.setTotal_net(0);
                paie.setTotal_impossable(0);
                paie.setCotisationCnss(0);
            }


            paiementRepository.saveAndFlush(paie);
            generatePdf(paie);
    }

    private int minEpocheTo(String begin, String end) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        var localTime1 = LocalTime.parse(begin, timeFormatter);
        var localTime2 = LocalTime.parse(end, timeFormatter);
        var minFin = localTime2.getHour() * 60 + localTime2.getMinute();
        var minDebut = localTime1.getHour() * 60 + localTime1.getMinute();
        return minFin - minDebut;
    }

    private int minEpocheAndTypeplaningTo(String begin, String end, int type) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        var localTime1 = LocalTime.parse(begin, timeFormatter);
        var localTime2 = LocalTime.parse(end, timeFormatter);
        var minFin = localTime2.getHour() * 60 + localTime2.getMinute();
        var minDebut = localTime1.getHour() * 60 + localTime1.getMinute();
        var diff = minFin - minDebut;
        var heureSupp = 0;
        if (type == 1) {
            if (diff > 8 * 60) {
                heureSupp = diff - (8 * 60);
            }
        } else {
            if (diff > 4 * 60) {
                heureSupp = diff - (4 * 60);
            }
        }
        return heureSupp;
    }

    @Override
    public List<PaiementModel> calculHeureSupp(int month, int year,Long id_user) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        var date_debut = LocalDate.of(year, month, 01);
        var date_fin = LocalDate.of(year, month, YearMonth.of(year, month).atEndOfMonth().getDayOfMonth());
        var employes = userRepository.findAll().stream().filter(e->e.isEnabled())
        .filter(f->f.getId() !=id_user)
        .collect(Collectors.toList());
        List<PaiementModel> paiementModels = new ArrayList<>();
        for (User user : employes) {
            var paie = paiementRepository.findOneByMonthAndYearAndUser(month, year, user);
            var heures = heureSupplRepository.findAllByUserAndDateHeureSupplBetween(user, date_debut, date_fin).stream()
                    .map(e -> minEpocheTo(e.getHeureDebut().toString(), e.getHeureFin().toString()))
                    .reduce(0, (x, y) -> x + y);
            var paiementModel = new PaiementModel(paie);
            paiementModel.setTotalHeureSuppl(heures);
            paiementModel.setPrime_HS(heures * 35);
            paiementModels.add(paiementModel);
        }
        return paiementModels;
    }

    @Override
    public List<PaiementModel> calculPrimeRetenue(int month, int year,Long id_user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calculPrimeRetenue'");
    }

    private void generatePdf(Paiement paiement) {
        Document document = new Document(PageSize.A4, 20, 20, 50, 20);

        try {
            Path file = Paths.get(filesPath);
            FileOutputStream pdfOutputFile = new FileOutputStream(filesPath + "/" + paiement.getId() + ".pdf");

            final PdfWriter pdfWriter = PdfWriter.getInstance(document, pdfOutputFile);
            Font font14 = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
            document.open(); // Open the Document
            //var month=YearMonth.of(paiement.getYear(), paiement.getMonth()).getMonth();
           // var start=paiement.getDatePaie().withDayOfMonth(1);
           //var lastday= paiement.getDatePaie().withDayOfMonth(paiement.getDatePaie().getMonth().length(paiement.getDatePaie().isLeapYear()));
            var date_debut = LocalDate.of(paiement.getYear(), paiement.getMonth(), 01);
            var date_fin = LocalDate.of(paiement.getYear(), paiement.getMonth(), YearMonth.of(paiement.getYear(), paiement.getMonth()).atEndOfMonth().getDayOfMonth());
            Chunk chunktitle = new Chunk("Fiche de paie "+date_debut+" -"+date_fin, font14);
            chunktitle.setUnderline(new Color(0x00, 0x00, 0x00), 2.0f, 0.0f, -5.0f, 0, PdfContentByte.LINE_CAP_ROUND);
            document.add(chunktitle);
            Paragraph paragraph = new Paragraph();
            document.setMargins(120, 120, 50, 80);
            paragraph.add("Nom: ");
            paragraph.add(new Phrase(paiement.getUser().getNom()));
            Paragraph paragraph2 = new Paragraph();
            paragraph2.add("Prenom: ");
            paragraph2.add(new Phrase(paiement.getUser().getPrenom()));
            Paragraph paragraph3 = new Paragraph();
            paragraph3.add("Téléphone: ");
            paragraph3.add(new Phrase(paiement.getUser().getTelephone()));
            document.add(paragraph);
            document.add(paragraph2);
            document.add(paragraph3);
            document.setMargins(120, 120, 50, 80);
            document.addTitle("Bulletin de paie");
            // document.setMargins(0, 0, 50, 80);
            float[] withs = { 0.1f, 0.4f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f };
            PdfPTable table = new PdfPTable(withs);
            table.setSpacingBefore(40);
            table.setSpacingAfter(30);
            table.setTotalWidth(500);
            table.setLockedWidth(true);
            table.setHeaderRows(1);
            Font font8 = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
            // Table table = new Table(7);
            /*
             * table.setBorderWidth(1);
             * table.setBorderColor(new Color(0, 0, 255));
             * table.setPadding(2);
             * table.setSpacing(0);
             */
            table.addCell(PdfUtil.addCell(new Phrase("Code", font8), true));
            table.addCell(PdfUtil.addCell(new Phrase("Designation", font8), true));
            table.addCell(PdfUtil.addCell(new Phrase("Jour", font8), true));
            table.addCell(PdfUtil.addCell(new Phrase("Heures", font8), true));
            table.addCell(PdfUtil.addCell(new Phrase("Montant", font8), true));
            table.addCell(PdfUtil.addCell(new Phrase("Brut", font8), true));
            table.addCell(PdfUtil.addCell(new Phrase("Net", font8), true));
            // table.endHeaders();
            //var presta = Math.round(paiement.getTotal_heure() * 17.5 * 100.0) / 100.0;
            var presta=paiement.getTotal_brut();
            table.addCell(PdfUtil.addCell(new Phrase("PR1"), false));
            table.addCell(PdfUtil.addCell(new Phrase("Prestation"), false));
            table.addCell(PdfUtil.addCell(new Phrase(""), false));
            table.addCell(PdfUtil.addCell(new Phrase((String.valueOf(paiement.getTotal_heure()))), false));
            table.addCell(PdfUtil.addCell(new Phrase(String.valueOf(paiement.getUser().getFonction().getSalaireHeure())), false));
            table.addCell(PdfUtil.addCell(new Phrase(String.valueOf(presta)), false));
            table.addCell(PdfUtil.addCell(new Phrase(String.valueOf(presta)), false));
            // Prime equipe
            var prime = Math.round(paiement.getTotal_heure() * 1.25 * 100.0) / 100.0;
            table.addCell(PdfUtil.addCell(new Phrase("EQ"), false));
            table.addCell(PdfUtil.addCell(new Phrase("Prime equipe"), false));
            table.addCell(PdfUtil.addCell(new Phrase(""), false));
            table.addCell(PdfUtil.addCell(new Phrase((String.valueOf(paiement.getTotal_heure()))), false));
            table.addCell(PdfUtil.addCell(new Phrase(String.valueOf(1.25)), false));
            table.addCell(PdfUtil.addCell(new Phrase(String.valueOf(prime)), false));
            table.addCell(PdfUtil.addCell(new Phrase(String.valueOf(prime)), false));
            // heure supp
           // var heuresupp = Math.round(paiement.getTotalHeureSuppl() * 35 * 100.0) / 100.0;
            var heuresupp=Math.round(paiement.getTotalHeureSuppl() * paiement.getUser().getFonction().getSalaireHeure()*2 * 100.0) / 100.0;
            table.addCell(PdfUtil.addCell(new Phrase("HSP"), false));
            table.addCell(PdfUtil.addCell(new Phrase("Prime HS"), false));
            table.addCell(PdfUtil.addCell(new Phrase(""), false));
            table.addCell(PdfUtil.addCell(new Phrase((String.valueOf(paiement.getTotalHeureSuppl()))), false));
            table.addCell(PdfUtil.addCell(new Phrase(String.valueOf(paiement.getUser().getFonction().getSalaireHeure()*2)), false));
            table.addCell(PdfUtil.addCell(new Phrase(String.valueOf(heuresupp)), false));
            table.addCell(PdfUtil.addCell(new Phrase(String.valueOf(heuresupp)), false));
            // suppl Transport
            var transp = Math.round(paiement.getTotalJours() * 1.62 * 100.0) / 100.0;
            table.addCell(PdfUtil.addCell(new Phrase("PR1"), false));
            table.addCell(PdfUtil.addCell(new Phrase("Supp Transport"), false));
            table.addCell(PdfUtil.addCell(new Phrase(""), false));
            table.addCell(PdfUtil.addCell(new Phrase((String.valueOf(paiement.getTotalJours()))), false));
            table.addCell(PdfUtil.addCell(new Phrase(String.valueOf(1.62)), false));
            table.addCell(PdfUtil.addCell(new Phrase(String.valueOf(transp)), false));
            table.addCell(PdfUtil.addCell(new Phrase(String.valueOf(transp)), false));
            // cheque repas
            // var check= Math.round(heure_pointe * 100.0) / 100.0;
            table.addCell(PdfUtil.addCell(new Phrase("MCW"), false));
            table.addCell(PdfUtil.addCell(new Phrase("Cheque repas"), false));
            table.addCell(PdfUtil.addCell(new Phrase(String.valueOf(paiement.getTotalJours())), false));
            table.addCell(PdfUtil.addCell(new Phrase(String.valueOf(paiement.getTotalJours() * 8)), false));
            table.addCell(PdfUtil.addCell(new Phrase(String.valueOf(8)), false));
            table.addCell(PdfUtil.addCell(new Phrase(String.valueOf(paiement.getTotalJours() * 8)), false));
            table.addCell(PdfUtil.addCell(new Phrase(String.valueOf(paiement.getTotalJours() * 8)), false));
            // total
            PdfPCell pdfPCell = new PdfPCell(new Phrase("Total"));
            pdfPCell.setColspan(2);

            table.addCell(pdfPCell);
            document.add(table);
            document.topMargin();
            Chunk calcul = new Chunk("Calcul", font14);
            chunktitle.setUnderline(new Color(0x00, 0x00, 0x00), 2.0f, 0.0f, -5.0f, 0, PdfContentByte.LINE_CAP_ROUND);
            document.add(calcul);
            document.add(new Paragraph(""));
            Paragraph salairebrut = new Paragraph();
            Chunk salbrutchunck = new Chunk("Salaire brut: ", font8);
            salairebrut.add(salbrutchunck);
            salairebrut.add(" " + paiement.getTotal_brut());
            document.add(salairebrut);

            Paragraph total_retenu = new Paragraph();
            Chunk total_retenuchunck = new Chunk("Total retenues: ", font8);
            total_retenu.add(total_retenuchunck);
            total_retenu.add(" " + paiement.getRetenu_total());
            document.add(total_retenu);

            Paragraph total_prime = new Paragraph();
            Chunk total_primechunck = new Chunk("Total primes: ", font8);
            total_prime.add(total_primechunck);
            total_prime.add(" " + paiement.getTotal_prime());
            document.add(total_prime);

            Paragraph salaireimposable = new Paragraph();
            Chunk salaireimposablechunck = new Chunk("Salaire Imposable: ", font8);
            salaireimposable.add(salaireimposablechunck);
            salaireimposable.add(" " + paiement.getTotal_impossable());
            document.add(salaireimposable);

            Paragraph net_payer = new Paragraph();
            Chunk net_payerchunck = new Chunk("Salaire net: ", font8);
            net_payer.add(net_payerchunck);
            net_payer.add(" " + paiement.getTotal_net());
            document.add(net_payer);

            Paragraph total_payer = new Paragraph();
            Chunk total_payerchunck = new Chunk("Total a payer: ", font8);
            total_payer.add(total_payerchunck);
            total_payer.add(" " + paiement.getTotal_net());
            document.add(total_payer);

            document.close(); // 5) Close the Document

            pdfWriter.close();// 6) close the File writer

        } catch (IOException de) {
            System.err.println(de.getMessage());
        }
    }

    // To send an email with attachment
    public String sendMailWithAttachment(EmailDetails details) {
        // Creating a mime message
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {

            // Setting multipart as true for attachments to
            // be send
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(details.getRecipient());
            mimeMessageHelper.setText(details.getMsgBody());
            mimeMessageHelper.setSubject(
                    details.getSubject());

            // Adding the attachment
            FileSystemResource file = new FileSystemResource(
                    new File(details.getAttachment()));

            mimeMessageHelper.addAttachment(
                    file.getFilename(), file);

            // Sending the mail
            javaMailSender.send(mimeMessage);
            return "Mail sent Successfully";
        }

        // Catch block to handle MessagingException
        catch (MessagingException e) {

            // Display message when exception occurred
            return "Error while sending mail!!!";
        }
    }

    @Override
    public List<PaiementModel> sendMail(int month, int year,Long id_user) {

        var employes = userRepository.findAll().stream()
        .filter(e->e.isEnabled()).filter(e->e.getId() !=id_user)
        .collect(Collectors.toList());
        List<PaiementModel> paiementModels = new ArrayList<>();
        for (User user : employes) {
            var paie = paiementRepository.findOneByMonthAndYearAndUser(month, year, user);
            var emailDetail = new EmailDetails();
            emailDetail.setMsgBody("");
            emailDetail.setRecipient(user.getEmail());
            emailDetail.setSubject("Bulletin de paie");
            emailDetail.setAttachment(filesPath + "/" + paie.getId() + ".pdf");
            sendMailWithAttachment(emailDetail);
            var paiementModel = new PaiementModel(paie);
            paiementModels.add(paiementModel);
            paie.setDatePaie(LocalDate.now());
            paie.setModifiedAt(LocalDateTime.now());
        }
        return paiementModels;
    }

    @Override
    public List<PaiementModel> generatePaie(int month, int year,Long id_user) {

        var employes = userRepository.findAll().stream().filter(e->e.isEnabled())
        .filter(f->f.getId() !=id_user)
        .collect(Collectors.toList());
        List<PaiementModel> paiementModels = new ArrayList<>();
        for (User user : employes) {
            var paie = paiementRepository.findOneByMonthAndYearAndUser(month, year, user);
            generatePdf(paie);
            var paiementModel = new PaiementModel(paie);
            paiementModels.add(paiementModel);
        }
        return paiementModels;
    }

    @Override
    public List<PaiementModel> paiementByUser(Long userid) {
        return paiementRepository.findAllByUser(userRepository.findById(userid).get()).stream().map(this::paiementToPaiementModel).collect(Collectors.toList());
    }

    @Override
    public PaiementModel sendBulletn(Long id) {
        var paie = paiementRepository.findById(id);
        var emailDetail = new EmailDetails();
        emailDetail.setMsgBody("");
        emailDetail.setRecipient(paie.get().getUser().getEmail());
        emailDetail.setSubject("Bulletin de paie");
        emailDetail.setAttachment(filesPath + "/" + id + ".pdf");
        sendMailWithAttachment(emailDetail);
        var paiementModel = new PaiementModel(paie.get());
        return paiementModel;
    }
}
