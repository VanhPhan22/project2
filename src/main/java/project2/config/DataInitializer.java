package project2.config;


import org.springframework.beans.factory.BeanRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import project2.entity.*;
import project2.enums.*;
import project2.repository.SpectatorRepo;
import project2.repository.TicketRepo;
import project2.repository.TournamentRepo;
import project2.service.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private AdminService adminService;
    @Autowired
    private HorseService horseService;
    @Autowired
    private HorseOwnerService horseOwnerService;
    @Autowired
    private JockeyService jockeyService;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private PredictionService predictionService;
    @Autowired
    private PrizeService prizeService;
    @Autowired
    private RaceParticipationService raceParticipationService;
    @Autowired
    private RaceRefereeService raceRefereeService;
    @Autowired
    private RaceResultService raceResultService;
    @Autowired
    private RaceScheduleService raceScheduleService;
    @Autowired
    private SpectatorService spectatorService;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private TournamentService tournamentService;
    @Autowired
    private TournamentRepo tournamentRepo;
    @Autowired
    private SpectatorRepo spectatorRepo;
    @Autowired
    private TicketRepo ticketRepo;

    @Override
    public void run(String... args) throws Exception {

        /*___________________________________________________________________________________________________________ */
        //                                                  ADMIN
        Admin ad1 = new Admin("tranvan_admin", "ad123", "tranavan.admin@duangua.vn");
        Admin ad2 = new Admin("lehoang_admin", "ad123", "lehoang.admin@duangua.vn");
        Admin ad3 = new Admin("phamthinh-admin", "ad123", "phamthinh.admin@duangua.vn");
        Admin ad4 = new Admin("nguyenbich_admin", "ad123", "nguyenbich.admin@duangua.vn");
        Admin ad5 = new Admin("tranduong_admin", "ad123", "tranduong.admin@duangua.vn");



        /*___________________________________________________________________________________________________________ */
        //                                                  SPECTATOR
        Spectator spec1 = new Spectator("khangia_tuan", "tuankh@gmail.com", "spec123");
        Spectator spec2 = new Spectator("yeuthethao99", "thethao99@gmail.com", "spec123");
        Spectator spec3 = new Spectator("ngoclinh_fan", "ngoclinh@gmail.com", "spec123");
        Spectator spec4 = new Spectator("huyhoang_bet", "huyhoang@gmail.com", "spec123");
        Spectator spec5 = new Spectator("mai_phuong22", "phuongmai@gmail.com", "spec123");
        Spectator spec6 = new Spectator("thantai_den", "thantai@gmail.com", "spec123");
        Spectator spec7 = new Spectator("quocbao_88", "quocbao88@gmail.com", "spec123");
        Spectator spec8 = new Spectator("minhhang_vip", "minhhang@gmail.com", "spec123");
        Spectator spec9 = new Spectator("trandung_win", "trandung@gmail.com", "spec123");
        Spectator spec10 = new Spectator("lelan_smile", "lelan@gmail.com", "spec123");


        /*___________________________________________________________________________________________________________ */
        //                                                  HORSE OWNER
        HorseOwner ho1 = new HorseOwner("Lý Gia Thành", "ThanhLGT", "0901112233", "ho123", "giathanh.owner@gmail.com");
        HorseOwner ho2 = new HorseOwner("Trương Đại Lộc", "LocTĐL", "0902223344", "ho123", "dailoc.owner@gmail.com");
        HorseOwner ho3 = new HorseOwner("Trần Phú Qúy", "QuyTPQ", "0903334455", "ho123", "phuquy.owner@gmail.com");
        HorseOwner ho4 = new HorseOwner("Nguyễn Tài Phát", "PhatNTP", "0904445566", "ho123", "taiphat.owner@gmail.com");


        /*___________________________________________________________________________________________________________ */
        //                                                  JOCKEY
        Jockey joc1 = new Jockey("Trần Kỵ Thương", "ThuongTKT", 25, 4, "0981112233", "Jock123");
        Jockey joc2 = new Jockey("Lê Bá Vũ", "VuLBV", 29, 8, "0982223344", "Jock123");
        Jockey joc3 = new Jockey("Nguyễn Phi Trường", "TruongNPT", 24, 3, "0983334455", "Jock123");
        Jockey joc4 = new Jockey("Phạm Dũng Cảm", "CamPDC", 32, 10, "0984445566", "Jock123");
        Jockey joc5 = new Jockey("Đinh Thần Tốc", "TocĐTT", 27, 6, "0985556677", "Jock123");
        Jockey joc6 = new Jockey("Vũ Phong Nhã", "NhaVPN", 26, 5, "0986667788", "Jock123");
        Jockey joc7 = new Jockey("Hồ Quang Uy", "UyHQU", 30, 9, "0987778899", "Jock123");
        Jockey joc8 = new Jockey("Lý Phi Long", "LongLPL", 23, 2, "0988889900", "Jock123");

        /*
        Jockey joc9 = new Jockey("Cao Vạn Dặm",       "DamCVD",     28, 7, "0989990011", "Jock123");
        Jockey joc10= new Jockey("Bùi Uy Dũng",       "DungBUD",    31, 11,"0981001122", "Jock123");

         */



        /*___________________________________________________________________________________________________________ */
        //                                                  RACE REFEREE
        RaceReferee ref1 = new RaceReferee("Lương Công Bằng", "BangLCB", "0911112233", "RaRe123", "congbang.ref@mail.com");
        RaceReferee ref2 = new RaceReferee("Trịnh Chính Trực", "TrucTCT", "0912223344", "RaRe123", "chinhtruc.ref@mail.com");
        RaceReferee ref3 = new RaceReferee("Tạ Khách Quan", "QuanTKQ", "0913334455", "RaRe123", "khachquan.ref@mail.com");
        RaceReferee ref4 = new RaceReferee("Võ Minh Bạch", "AchVMB", "0914445566", "RaRe123", "minhbach.ref@mail.com");
        RaceReferee ref5 = new RaceReferee("Đào Công Tâm", "AmĐCT", "0915556677", "RaRe123", "congtam.ref@mail.com");
        RaceReferee ref6 = new RaceReferee("Phan Trung Lập", "LapPTL", "0916667788", "RaRe123", "trunglap.ref@mail.com");
        RaceReferee ref7 = new RaceReferee("Bùi Đức Minh", "InhBĐM", "0917778899", "RaRe123", "duicminh.ref@mail.com");
        RaceReferee ref8 = new RaceReferee("Đoàn Quang Sang", "SangĐQS", "0918889900", "RaRe123", "quangsang.ref@mail.com");
        RaceReferee ref9 = new RaceReferee("Chu Uy Nghiêm", "IemCUN", "0919990011", "RaRe123", "uynghiem.ref@mail.com");
        RaceReferee ref10 = new RaceReferee("Lâm Quyết Đoán", "DoanLQĐ", "0911001122", "RaRe123", "quyetdoan.ref@mail.com");



        /*___________________________________________________________________________________________________________ */
        //                                                  HORSE
        Horse h1 = new Horse("Bão Táp", 4, "Thoroughbred", "Khỏe mạnh");
        Horse h2 = new Horse("Sấm Sét", 5, "Quarter Horse", "Khỏe mạnh");
        Horse h3 = new Horse("Xích Thố", 3, "Arabian", "Khỏe mạnh");
        Horse h4 = new Horse("Bạch Mã", 6, "Thoroughbred", "Đang hồi phục");
        Horse h5 = new Horse("Phi Yến", 4, "Appaloosa", "Khỏe mạnh");
        Horse h6 = new Horse("Hắc Ám", 5, "Thoroughbred", "Chấn thương nhẹ");
        Horse h7 = new Horse("Tia Chớp", 3, "Quarter Horse", "Khỏe mạnh");
        Horse h8 = new Horse("Đại Bàng", 7, "Arabian", "Khỏe mạnh");


        /*___________________________________________________________________________________________________________ */
        //                                                  TOURNAMENT

        Tournament tour1 = new Tournament("Giải Vô Địch Quốc Gia 2025", "Trường đua Phú Thọ", LocalDate.of(2025, 12, 20), LocalDate.of(2025, 12, 25));
        Tournament tour2 = new Tournament("Giải Đua Ngựa Khai Xuân 2026", "Trường đua Đại Nam", LocalDate.of(2026, 2, 10), LocalDate.of(2026, 2, 15));
        Tournament tour3 = new Tournament("Giải Đua Ngựa Đón Hè 2026", "Trường đua Phú Thọ", LocalDate.of(2026, 5, 15), LocalDate.of(2026, 5, 20));
        Tournament tour4 = new Tournament("Giải Đua Ngựa Vượt Thu 2026", "Trường đua Sóc Sơn", LocalDate.of(2026, 8, 20), LocalDate.of(2026, 8, 25));
        Tournament tour5 = new Tournament("Giải Đua Ngựa Chinh Đông 2026", "Trường đua Đại Nam", LocalDate.of(2026, 11, 5), LocalDate.of(2026, 11, 10));
        Tournament tour6 = new Tournament("Giải Vô Địch Quốc Gia 2026", "Trường đua Phú Thọ", LocalDate.of(2026, 12, 20), LocalDate.of(2026, 12, 25));





        /*___________________________________________________________________________________________________________ */
        //                                                  PRIZE
        Prize p1 = new Prize("Giải Nhất", BigDecimal.valueOf(2500000000.00), 1);
        Prize p2 = new Prize("Giải Nhì", BigDecimal.valueOf(1375000000.00), 2);
        Prize p3 = new Prize("Giải Ba", BigDecimal.valueOf(937500000.00), 3);
        Prize p4 = new Prize("Giải Tư", BigDecimal.valueOf(562500000.00), 4);
        Prize p5 = new Prize("Giải Năm", BigDecimal.valueOf(375000000.00), 5);
        Prize p6 = new Prize("Giải Sáu", BigDecimal.valueOf(375000000.00), 6);
        Prize p7 = new Prize("Giải Bảy", BigDecimal.valueOf(375000000.00), 7);
        Prize p8 = new Prize("Giải Tám", BigDecimal.valueOf(375000000.00), 8);

        /*___________________________________________________________________________________________________________ */
        //                                                  TICKET

        //tour1, spec1, spec2
        Ticket t1 = new Ticket(BigDecimal.valueOf(200000), LocalDateTime.of(2025, 12, 16, 11, 45), TicketStatus.USED);
        Ticket t2 = new Ticket(BigDecimal.valueOf(200000), LocalDateTime.of(2025, 12, 17, 11, 45), TicketStatus.USED);

        //tour2, spec3, spec4
        Ticket t3 = new Ticket(BigDecimal.valueOf(100000), LocalDateTime.of(2026, 2, 1, 9, 0), TicketStatus.USED);
        Ticket t4 = new Ticket(BigDecimal.valueOf(100000), LocalDateTime.of(2026, 2, 2, 9, 0), TicketStatus.CANCELLED);

        //tour3, spec5, spec6
        Ticket t5 = new Ticket(BigDecimal.valueOf(150000), LocalDateTime.of(2026, 5, 12, 14, 30), TicketStatus.USED);
        Ticket t6 = new Ticket(BigDecimal.valueOf(150000), LocalDateTime.of(2026, 5, 13, 14, 30), TicketStatus.USED);

        //tour4, spec7, spec8
        Ticket t7 = new Ticket(BigDecimal.valueOf(120000), LocalDateTime.of(2026, 8, 11, 10, 15), TicketStatus.SOLD);
        Ticket t8 = new Ticket(BigDecimal.valueOf(120000), LocalDateTime.of(2026, 8, 11, 10, 15), TicketStatus.SOLD);

        //tour5, spec9, spec10
        Ticket t9 = new Ticket(BigDecimal.valueOf(120000), LocalDateTime.of(2026, 11, 1, 16, 0), TicketStatus.SOLD);
        Ticket t10 = new Ticket(BigDecimal.valueOf(120000), LocalDateTime.of(2026, 11, 2, 16, 0), TicketStatus.SOLD);

        //tour6, spec1, spec2
        Ticket t11 = new Ticket(BigDecimal.valueOf(200000), LocalDateTime.of(2026, 12, 16, 11, 45), TicketStatus.SOLD);
        Ticket t12 = new Ticket(BigDecimal.valueOf(200000), LocalDateTime.of(2026, 12, 17, 11, 45), TicketStatus.SOLD);



        /*___________________________________________________________________________________________________________ */
        //                                                  RACE SCHEDULE

        RaceSchedule rasc1 = new RaceSchedule(
                "Bán kết Quốc gia 2025 lần 1",
                LocalDate.of(2025, 12, 22),
                "Trường đua Phú Thọ",
                RaceScheduleStatus.COMPLETED,
                LocalDateTime.of(2025, 12, 22, 15, 0, 0),
                LocalDateTime.of(2025, 12, 22, 17, 0, 0)
        );


        RaceSchedule rasc2 = new RaceSchedule(
                "Bán kết Quốc gia 2025 lần 2",
                LocalDate.of(2025, 12, 23),
                "Trường đua Phú Thọ",
                RaceScheduleStatus.COMPLETED,
                LocalDateTime.of(2025, 12, 23, 15, 0, 0),
                LocalDateTime.of(2025, 12, 23, 17, 0, 0)
        );


        RaceSchedule rasc3 = new RaceSchedule(
                "Chung kết Quốc gia 2025",
                LocalDate.of(2025, 12, 24),
                "Trường đua Phú Thọ",
                RaceScheduleStatus.COMPLETED,
                LocalDateTime.of(2025, 12, 24, 15, 0, 0),
                LocalDateTime.of(2025, 12, 24, 17, 0, 0)
        );


        RaceSchedule rasc4 = new RaceSchedule(
                "Chung kết Xuân 2026",
                LocalDate.of(2026, 2, 14),
                "Trường đua Đại Nam",
                RaceScheduleStatus.COMPLETED,
                LocalDateTime.of(2026, 2, 14, 15, 0, 0),
                LocalDateTime.of(2026, 2, 14, 17, 0, 0)
        );


        RaceSchedule rasc5 = new RaceSchedule(
                "Chung kết Hè 2026",
                LocalDate.of(2026, 5, 19),
                "Trường đua Phú Thọ",
                RaceScheduleStatus.COMPLETED,
                LocalDateTime.of(2026, 5, 19, 15, 0, 0),
                LocalDateTime.of(2026, 5, 19, 17, 0, 0)
        );


        RaceSchedule rasc6 = new RaceSchedule(
                "Chung kết Thu 2026",
                LocalDate.of(2026, 8, 24),
                "Trường đua Sóc Sơn",
                RaceScheduleStatus.PENDING,
                LocalDateTime.of(2026, 8, 24, 15, 0, 0),
                LocalDateTime.of(2026, 8, 24, 17, 0, 0)
        );


        RaceSchedule rasc7 = new RaceSchedule(
                "Chung kết Đông 2026",
                LocalDate.of(2026, 11, 9),
                "Trường đua Đại Nam",
                RaceScheduleStatus.PENDING,
                LocalDateTime.of(2026, 11, 9, 15, 0, 0),
                LocalDateTime.of(2026, 11, 9, 17, 0, 0)
        );


        RaceSchedule rasc8 = new RaceSchedule(
                "Bán kết Quốc gia 2026 lần 1",
                LocalDate.of(2026, 12, 22),
                "Trường đua Phú Thọ",
                RaceScheduleStatus.PENDING,
                LocalDateTime.of(2026, 12, 22, 15, 0, 0),
                LocalDateTime.of(2026, 12, 22, 17, 0, 0)
        );


        RaceSchedule rasc9 = new RaceSchedule(
                "Bán kết Quốc gia 2026 lần 2",
                LocalDate.of(2026, 12, 23),
                "Trường đua Phú Thọ",
                RaceScheduleStatus.PENDING,
                LocalDateTime.of(2026, 12, 23, 15, 0, 0),
                LocalDateTime.of(2026, 12, 23, 17, 0, 0)
        );


        RaceSchedule rasc10 = new RaceSchedule(
                "Chung kết Quốc gia 2026",
                LocalDate.of(2026, 12, 24),
                "Trường đua Phú Thọ",
                RaceScheduleStatus.PENDING,
                LocalDateTime.of(2026, 12, 24, 15, 0, 0),
                LocalDateTime.of(2026, 12, 24, 17, 0, 0)
        );


        /*___________________________________________________________________________________________________________ */
        //                                                  RACE PARITIPATION

        /*A - B - C */ //tham chiếu đến RaceScheduleId - HorseId - JockeyId

        //Bán kết Quốc gia 2025 lần 1
        /*1 - 1 - 1*/
        RaceParticipation rapa1 = new RaceParticipation(RaceParticipationStatus.CONFIRMED, 1);
        /*1 - 2 - 2*/
        RaceParticipation rapa2 = new RaceParticipation(RaceParticipationStatus.CONFIRMED, 2);
        /*1 - 3 - 3*/
        RaceParticipation rapa3 = new RaceParticipation(RaceParticipationStatus.CONFIRMED, 3);
        /*1 - 4 - 4*/
        RaceParticipation rapa4 = new RaceParticipation(RaceParticipationStatus.CONFIRMED, 4);

        //Bán kết Quốc gia 2025 lần 2
        /*2 - 5 - 5*/
        RaceParticipation rapa5 = new RaceParticipation(RaceParticipationStatus.CONFIRMED, 1);
        /*2 - 6 - 6*/
        RaceParticipation rapa6 = new RaceParticipation(RaceParticipationStatus.CONFIRMED, 2);
        /*2 - 7 - 7*/
        RaceParticipation rapa7 = new RaceParticipation(RaceParticipationStatus.CONFIRMED, 3);
        /*2 - 8 - 8*/
        RaceParticipation rapa8 = new RaceParticipation(RaceParticipationStatus.CONFIRMED, 4);

        //Chung kết Quốc gia 2025
        /*3 - 1 - 1*/
        RaceParticipation rapa9 = new RaceParticipation(RaceParticipationStatus.CONFIRMED, 1);
        /*3 - 2 - 2*/
        RaceParticipation rapa10 = new RaceParticipation(RaceParticipationStatus.CONFIRMED, 2);
        /*3 - 5 - 5*/
        RaceParticipation rapa11 = new RaceParticipation(RaceParticipationStatus.CONFIRMED, 3);
        /*3 - 6 - 6*/
        RaceParticipation rapa12 = new RaceParticipation(RaceParticipationStatus.CONFIRMED, 4);

        //Chung kết Xuân 2026
        /*4 - 1 - 1*/
        RaceParticipation rapa13 = new RaceParticipation(RaceParticipationStatus.CONFIRMED, 1);
        /*4 - 2 - 2*/
        RaceParticipation rapa14 = new RaceParticipation(RaceParticipationStatus.CONFIRMED, 2);
        /*4 - 3 - 3*/
        RaceParticipation rapa15 = new RaceParticipation(RaceParticipationStatus.CONFIRMED, 3);
        /*4 - 4 - 4*/
        RaceParticipation rapa16 = new RaceParticipation(RaceParticipationStatus.CONFIRMED, 4);

        //Chung kết Hè 2026
        /*5 - 5 - 5*/
        RaceParticipation rapa17 = new RaceParticipation(RaceParticipationStatus.CONFIRMED, 1);
        /*5 - 6 - 6*/
        RaceParticipation rapa18 = new RaceParticipation(RaceParticipationStatus.CONFIRMED, 2);
        /*5 - 7 - 7*/
        RaceParticipation rapa19 = new RaceParticipation(RaceParticipationStatus.CONFIRMED, 3);
        /*5 - 8 - 8*/
        RaceParticipation rapa20 = new RaceParticipation(RaceParticipationStatus.CONFIRMED, 4);

        //Chung kết Thu 2026
        /*6 - 1 - 1*/
        RaceParticipation rapa21 = new RaceParticipation(RaceParticipationStatus.CONFIRMED, 1);
        /*6 - 2 - 2*/
        RaceParticipation rapa22 = new RaceParticipation(RaceParticipationStatus.CONFIRMED, 2);
        /*6 - 3 - 3*/
        RaceParticipation rapa23 = new RaceParticipation(RaceParticipationStatus.CONFIRMED, 3);
        /*6 - 4 - 4*/
        RaceParticipation rapa24 = new RaceParticipation(RaceParticipationStatus.CONFIRMED, 4);

        //Chung kết Đông 2026
        /*7 - 1 - 1*/
        RaceParticipation rapa25 = new RaceParticipation(RaceParticipationStatus.CONFIRMED, 1);
        /*7 - 2 - 2*/
        RaceParticipation rapa26 = new RaceParticipation(RaceParticipationStatus.CONFIRMED, 2);
        /*7 - 3 - 3*/
        RaceParticipation rapa27 = new RaceParticipation(RaceParticipationStatus.CONFIRMED, 3);
        /*7 - 4 - 4*/
        RaceParticipation rapa28 = new RaceParticipation(RaceParticipationStatus.CONFIRMED, 4);

        //Bán kết Quốc gia 2026 lần 1
        /*8 - 1 - 1*/
        RaceParticipation rapa29 = new RaceParticipation(RaceParticipationStatus.CONFIRMED, 1);
        /*8 - 2 - 2*/
        RaceParticipation rapa30 = new RaceParticipation(RaceParticipationStatus.CONFIRMED, 2);
        /*8 - 3 - 3*/
        RaceParticipation rapa31 = new RaceParticipation(RaceParticipationStatus.CONFIRMED, 3);
        /*8 - 4 - 4*/
        RaceParticipation rapa32 = new RaceParticipation(RaceParticipationStatus.CONFIRMED, 4);

        //Bán kết Quốc gia 2026 lần 2
        /*9 - 5 - 5*/
        RaceParticipation rapa33 = new RaceParticipation(RaceParticipationStatus.CONFIRMED, 1);
        /*9 - 6 - 6*/
        RaceParticipation rapa34 = new RaceParticipation(RaceParticipationStatus.CONFIRMED, 2);
        /*9 - 7 - 7*/
        RaceParticipation rapa35 = new RaceParticipation(RaceParticipationStatus.CONFIRMED, 3);
        /*9 - 8 - 8*/
        RaceParticipation rapa36 = new RaceParticipation(RaceParticipationStatus.CONFIRMED, 4);



        /*___________________________________________________________________________________________________________ */
        //                                                  RACE RESULT
        /*A - B - C */ //tham chiếu đến RaceScheduleId - RaceRefereeId - RaceParticipationId

        //Bán kết Quốc gia 2025 lần 1
        /*1 - 1 - 1*/
        RaceResult rare1 = new RaceResult(1, LocalTime.of(0, 4, 50, 120_000_000), RaceResultStatus.OFFICIAL);
        /*1 - 1 - 2*/
        RaceResult rare2 = new RaceResult(2, LocalTime.of(0, 4, 50, 350_000_000), RaceResultStatus.OFFICIAL);
        /*1 - 1 - 3*/
        RaceResult rare3 = new RaceResult(3, LocalTime.of(0, 4, 51, 140_000_000), RaceResultStatus.OFFICIAL);
        /*1 - 1 - 4*/
        RaceResult rare4 = new RaceResult(4, LocalTime.of(0, 4, 55, 150_000_000), RaceResultStatus.OFFICIAL);

        //Bán kết Quốc gia 2025 lần 2
        /*2 - 2 - 5*/
        RaceResult rare5 = new RaceResult(4, LocalTime.of(0, 5, 55, 150_000_000), RaceResultStatus.OFFICIAL);
        /*2 - 2 - 6*/
        RaceResult rare6 = new RaceResult(3, LocalTime.of(0, 5, 51, 140_000_000), RaceResultStatus.OFFICIAL);
        /*2 - 2 - 7*/
        RaceResult rare7 = new RaceResult(2, LocalTime.of(0, 5, 50, 350_000_000), RaceResultStatus.OFFICIAL);
        /*2 - 2 - 8*/
        RaceResult rare8 = new RaceResult(1, LocalTime.of(0, 5, 50, 120_000_000), RaceResultStatus.OFFICIAL);

        //Chung kết Quốc gia 2025
        /*3 - 3 - 9*/
        RaceResult rare9 = new RaceResult(2, LocalTime.of(0, 6, 50, 350_000_000), RaceResultStatus.OFFICIAL);
        /*3 - 3 - 10*/
        RaceResult rare10 = new RaceResult(3, LocalTime.of(0, 6, 51, 140_000_000), RaceResultStatus.OFFICIAL);
        /*3 - 3 - 11*/
        RaceResult rare11 = new RaceResult(4, LocalTime.of(0, 6, 55, 150_000_000), RaceResultStatus.OFFICIAL);
        /*3 - 3 - 12*/
        RaceResult rare12 = new RaceResult(1, LocalTime.of(0, 6, 50, 120_000_000), RaceResultStatus.OFFICIAL);

        //Chung kết Xuân 2026
        /*4 - 4 - 13*/
        RaceResult rare13 = new RaceResult(3, LocalTime.of(0, 5, 51, 140_000_000), RaceResultStatus.OFFICIAL);
        /*4 - 4 - 14*/
        RaceResult rare14 = new RaceResult(4, LocalTime.of(0, 5, 55, 150_000_000), RaceResultStatus.OFFICIAL);
        /*4 - 4 - 15*/
        RaceResult rare15 = new RaceResult(1, LocalTime.of(0, 5, 50, 120_000_000), RaceResultStatus.OFFICIAL);
        /*4 - 4 - 16*/
        RaceResult rare16 = new RaceResult(2, LocalTime.of(0, 5, 50, 350_000_000), RaceResultStatus.OFFICIAL);

        //Chung kết Hè 2026
        /*5 - 5 - 17*/
        RaceResult rare17 = new RaceResult(4, LocalTime.of(0, 6, 55, 150_000_000), RaceResultStatus.OFFICIAL);
        /*5 - 5 - 18*/
        RaceResult rare18 = new RaceResult(1, LocalTime.of(0, 6, 50, 120_000_000), RaceResultStatus.OFFICIAL);
        /*5 - 5 - 19*/
        RaceResult rare19 = new RaceResult(2, LocalTime.of(0, 6, 50, 350_000_000), RaceResultStatus.OFFICIAL);
        /*5 - 5 - 20*/
        RaceResult rare20 = new RaceResult(3, LocalTime.of(0, 6, 51, 140_000_000), RaceResultStatus.OFFICIAL);

        //Chung kết Thu 2026
        /*6 - 6 - 21*/
        RaceResult rare21 = new RaceResult(null, null, RaceResultStatus.UNOFFICIAL);
        /*6 - 6 - 22*/
        RaceResult rare22 = new RaceResult(null, null, RaceResultStatus.UNOFFICIAL);
        /*6 - 6 - 23*/
        RaceResult rare23 = new RaceResult(null, null, RaceResultStatus.UNOFFICIAL);
        /*6 - 6 - 24*/
        RaceResult rare24 = new RaceResult(null, null, RaceResultStatus.UNOFFICIAL);

        //Chung Kết Đông 2026
        /*7 - 7 - 25*/
        RaceResult rare25 = new RaceResult(null, null, RaceResultStatus.UNOFFICIAL);
        /*7 - 7 - 26*/
        RaceResult rare26 = new RaceResult(null, null, RaceResultStatus.UNOFFICIAL);
        /*7 - 7 - 27*/
        RaceResult rare27 = new RaceResult(null, null, RaceResultStatus.UNOFFICIAL);
        /*7 - 7 - 28*/
        RaceResult rare28 = new RaceResult(null, null, RaceResultStatus.UNOFFICIAL);

        //Bán kết Quốc gia 2026 lần 1
        /*8 - 8 - 29*/
        RaceResult rare29 = new RaceResult(null, null, RaceResultStatus.UNOFFICIAL);
        /*8 - 8 - 30*/
        RaceResult rare30 = new RaceResult(null, null, RaceResultStatus.UNOFFICIAL);
        /*8 - 8 - 31*/
        RaceResult rare31 = new RaceResult(null, null, RaceResultStatus.UNOFFICIAL);
        /*8 - 8 - 32*/
        RaceResult rare32 = new RaceResult(null, null, RaceResultStatus.UNOFFICIAL);

        //Bán kết Quốc gia 2026 lần 2
        /*9 - 9 - 33*/
        RaceResult rare33 = new RaceResult(null, null, RaceResultStatus.UNOFFICIAL);
        /*9 - 9 - 34*/
        RaceResult rare34 = new RaceResult(null, null, RaceResultStatus.UNOFFICIAL);
        /*9 - 9 - 35*/
        RaceResult rare35 = new RaceResult(null, null, RaceResultStatus.UNOFFICIAL);
        /*9 - 9 - 36*/
        RaceResult rare36 = new RaceResult(null, null, RaceResultStatus.UNOFFICIAL);



        /*___________________________________________________________________________________________________________ */
        //                                                  PREDICTION


        /*A - B - C */ //tham chiếu đến RaceScheduleId - HorseId - SpectatorId


        /*3 - 1 - 1*/
        Prediction pre1 = new Prediction(1, LocalDateTime.of(2025, 12, 16, 11, 45), PredictionStatus.WON);
        /*3 - 2 - 2*/
        Prediction pre2 = new Prediction(2, LocalDateTime.of(2025, 12, 17, 11, 45), PredictionStatus.LOST);
        /*4 - 3 - 3*/
        Prediction pre3 = new Prediction(3, LocalDateTime.of(2026, 2, 1, 9, 0), PredictionStatus.WON);
        /*4 - 4 - 4*/
        Prediction pre4 = new Prediction(4, LocalDateTime.of(2026, 2, 2, 9, 0), PredictionStatus.WON);
        /*5 - 7 - 5*/
        Prediction pre5 = new Prediction(5, LocalDateTime.of(2026, 5, 12, 14, 30), PredictionStatus.WON);
        /*5 - 8 - 6*/
        Prediction pre6 = new Prediction(6, LocalDateTime.of(2026, 5, 13, 14, 30), PredictionStatus.LOST);


        /*6 - 3 - 7*/
        Prediction pre7 = new Prediction(7, LocalDateTime.of(2026, 8, 11, 10, 15), PredictionStatus.PENDING);
        /*6 - 4 - 8*/
        Prediction pre8 = new Prediction(8, LocalDateTime.of(2026, 8, 12, 10, 15), PredictionStatus.PENDING);
        /*7 - 5 - 9*/
        Prediction pre9 = new Prediction(9, LocalDateTime.of(2026, 11, 1, 16, 0), PredictionStatus.PENDING);
        /*7 - 6 - 10*/
        Prediction pre10 = new Prediction(10, LocalDateTime.of(2026, 11, 1, 16, 0), PredictionStatus.PENDING);
        /*8 - 1 - 1*/
        Prediction pre11 = new Prediction(11, LocalDateTime.of(2026, 12, 16, 11, 45), PredictionStatus.PENDING);
        /*9 - 5 - 2*/
        Prediction pre12 = new Prediction(12, LocalDateTime.of(2026, 12, 17, 11, 45), PredictionStatus.PENDING);




        /*___________________________________________________________________________________________________________ */
        //                                                  NOTIFICATION














    }



}