/*
 * By Mas Elkhanza
 */
package rekammedis;

import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import kepegawaian.DlgCariDokter;
import kepegawaian.DlgCariPetugas;
import kepegawaian.DlgCariPegawai;

/**
 *
 * @author perpustakaan
 */
public final class RMPelaksanaanInformasiEdukasiModif extends javax.swing.JDialog {

    private final DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i = 0;
    private DlgCariDokter dokter;
    private DlgCariPetugas petugas;
    private DlgCariPegawai pegawai;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private volatile boolean ceksukses = false;
    private StringBuilder htmlContent;
    private String finger = "";
    private String TANGGALMUNDUR = "yes";

    /**
     * Creates new form DlgRujuk
     *
     * @param parent
     * @param modal
     */
    public RMPelaksanaanInformasiEdukasiModif(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode = new DefaultTableModel(null, new Object[]{
            "No.Rawat",
            "No.RM",
            "Nama Pasien",
            "Tanggal Lahir",
            "JK",
            "Tanggal",
            "Diagnosis Penyakit",
            "KIE Diagnosis Penyakit",
            "NIP Diagnosis",
            "Petugas Diagnosis",
            "Tanda Gejala Penyakit",
            "KIE Tanda Gejala Penyakit",
            "NIP Tanda Gejala",
            "Petugas Tanda Gejala",
            "Rencana Tindakan Medis",
            "KIE Rencana Tindakan Medis",
            "NIP Rencana Medis",
            "Petugas Rencana Medis",
            "Pengobatan Diberikan",
            "KIE Pengobatan Diberikan",
            "NIP Pengobatan",
            "Petugas Pengobatan",
            "Proses Perawatan",
            "KIE Proses Perawatan",
            "NIP Proses Perawatan",
            "Petugas Proses Perawatan",
            "Alternatif Pengobatan",
            "KIE Alternatif Pengobatan",
            "NIP Alternatif Pengobatan",
            "Petugas Alternatif Pengobatan",
            "Cara Penggunaan Obat",
            "KIE Cara Penggunaan Obat",
            "NIP Cara Penggunaan",
            "Petugas Cara Penggunaan",
            "Dosis Jadwal Obat",
            "KIE Dosis Jadwal Obat",
            "NIP Dosis",
            "Petugas Dosis",
            "Efek Samping Obat",
            "KIE Efek Samping Obat",
            "NIP Efek Samping",
            "Petugas Efek Samping",
            "Diet Nutrisi",
            "KIE Diet Nutrisi",
            "NIP Diet",
            "Petugas Diet",
            "Aktivitas Mobilisasi",
            "KIE Aktivitas Mobilisasi",
            "NIP Mobilisasi",
            "Petugas Mobilisasi",
            "Perawatan Luka",
            "KIE Perawatan Luka",
            "NIP Perawatan Luka",
            "Petugas Perawatan Luka",
            "Penggunaan Alat Medis",
            "KIE Penggunaan Alat Medis",
            "NIP Penggunaan Alat",
            "Petugas Penggunaan Alat",
            "Pencegahan Komplikasi",
            "KIE Pencegahan Komplikasi",
            "NIP Pencegahan Komplikasi",
            "Petugas Pencegahan Komplikasi",
            "Manajemen Nyeri",
            "KIE Manajemen Nyeri",
            "NIP Manajemen Nyeri",
            "Petugas Manajemen Nyeri",
            "Kebersihan Diri",
            "KIE Kebersihan Diri",
            "NIP Kebersihan",
            "Petugas Kebersihan",
            "Penundaan Pelayanan",
            "KIE Penundaan Pelayanan",
            "NIP Penundaan",
            "Petugas Penundaan",
            "Hambatan Pelayanan",
            "KIE Hambatan Pelayanan",
            "NIP Hambatan",
            "Petugas Hambatan",
            "Pencegahan Resiko Jatuh",
            "KIE Pencegahan Resiko Jatuh",
            "NIP Resiko Jatuh",
            "Petugas Resiko Jatuh",
            "Lain-Lain",
            "KIE Lain-Lain",
            "NIP Lain-Lain",
            "Petugas Lain-Lain",
            "Lain-Lain 1",
            "KIE Lain-Lain 1",
            "NIP Lain-Lain 1",
            "Petugas Lain-Lain 1",
            "Lain-Lain 2",
            "KIE Lain-Lain 2",
            "NIP Lain-Lain 2",
            "Petugas Lain-Lain 2",
            "Lain-Lain 3",
            "KIE Lain-Lain 3",
            "NIP Lain-Lain 3",
            "Petugas Lain-Lain 3"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex
            ) {
                return false;
            }
        };

        tbObat.setModel(tabMode);

        tbObat.setPreferredScrollableViewportSize(
                new Dimension(500, 500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0;
                i < tbObat.getColumnCount();
                i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);

            if (i == 0) {
                column.setPreferredWidth(120); // No Rawat
            } else if (i == 1) {
                column.setPreferredWidth(100); // No RM
            } else if (i == 2) {
                column.setPreferredWidth(180); // Nama Pasien
            } else if (i == 3) {
                column.setPreferredWidth(100); // Tgl Lahir
            } else if (i == 4) {
                column.setPreferredWidth(80); // JK
            } else if (i == 5) {
                column.setPreferredWidth(120); // Tanggal
            } else {
                String namaKolom = tbObat.getColumnName(i);

                if (namaKolom.startsWith("NIP")) {
                    column.setPreferredWidth(120);
                } else if (namaKolom.startsWith("Petugas")) {
                    column.setPreferredWidth(180);
                } else if (namaKolom.startsWith("KIE")) {
                    column.setPreferredWidth(250);
                } else {
                    column.setPreferredWidth(220);
                }
            }
        }

        tbObat.setDefaultRenderer(Object.class,
                new WarnaTable());

        TNoRw.setDocument(
                new batasInput((byte) 17).getKata(TNoRw)
        );

        TCari.setDocument(
                new batasInput((int) 100).getKata(TCari)
        );

        HTMLEditorKit kit = new HTMLEditorKit();

        LoadHTML.setEditable(
                true);
        LoadHTML.setEditorKit(kit);

        LoadHTML2.setEditable(
                true);
        LoadHTML2.setEditorKit(kit);
        StyleSheet styleSheet = kit.getStyleSheet();

        styleSheet.addRule(
                ".isi td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-bottom: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"
                + ".isi2 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#323232;}"
                + ".isi3 td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"
                + ".isi4 td{font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"
                + ".isi5 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#AA0000;}"
                + ".isi6 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#FF0000;}"
                + ".isi7 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#C8C800;}"
                + ".isi8 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#00AA00;}"
                + ".isi9 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#969696;}"
        );
        Document doc = kit.createDefaultDocument();

        LoadHTML.setDocument(doc);

        LoadHTML2.setDocument(doc);

        ChkAccor.setSelected(
                false);
        isPhoto();

        try {
            TANGGALMUNDUR = koneksiDB.TANGGALMUNDUR();
        } catch (Exception e) {
            TANGGALMUNDUR = "yes";
        }

        jam();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LoadHTML = new widget.editorpane();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnCetakKie = new javax.swing.JMenuItem();
        TanggalRegistrasi = new widget.TextBox();
        internalFrame1 = new widget.InternalFrame();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        TabRawat = new javax.swing.JTabbedPane();
        internalFrame2 = new widget.InternalFrame();
        scrollInput = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        jSeparator14 = new javax.swing.JSeparator();
        TNoRw = new widget.TextBox();
        TPasien = new widget.TextBox();
        TNoRM = new widget.TextBox();
        jLabel8 = new widget.Label();
        TglLahir = new widget.TextBox();
        Jk = new widget.TextBox();
        jLabel10 = new widget.Label();
        label11 = new widget.Label();
        jLabel11 = new widget.Label();
        TglEdukasi = new widget.Tanggal();
        BtnDokter = new widget.Button();
        NmDiagnosa = new widget.TextBox();
        NipDiagnosa = new widget.TextBox();
        label15 = new widget.Label();
        jSeparator11 = new javax.swing.JSeparator();
        label16 = new widget.Label();
        label17 = new widget.Label();
        label18 = new widget.Label();
        jLabel22 = new widget.Label();
        Kie_Hambatan_Pelayanan = new widget.ComboBox();
        Hambatan_Pelayanan = new widget.ComboBox();
        jLabel23 = new widget.Label();
        label19 = new widget.Label();
        label20 = new widget.Label();
        jLabel24 = new widget.Label();
        Tanda_Gejala_Penyakit = new widget.ComboBox();
        jLabel25 = new widget.Label();
        Kie_Tanda_Gejala_Penyakit = new widget.ComboBox();
        label21 = new widget.Label();
        label22 = new widget.Label();
        jLabel26 = new widget.Label();
        Ren_Tindakan_Medis = new widget.ComboBox();
        jLabel27 = new widget.Label();
        Kie_Ren_Tindakan_Medis = new widget.ComboBox();
        label23 = new widget.Label();
        label24 = new widget.Label();
        jLabel28 = new widget.Label();
        Pengobatan_Diberikan = new widget.ComboBox();
        jLabel29 = new widget.Label();
        Kie_Pengobatan_Diberikan = new widget.ComboBox();
        label25 = new widget.Label();
        label26 = new widget.Label();
        jLabel30 = new widget.Label();
        Proses_Perawatan = new widget.ComboBox();
        jLabel31 = new widget.Label();
        Kie_Proses_Perawatan = new widget.ComboBox();
        label27 = new widget.Label();
        label28 = new widget.Label();
        jLabel32 = new widget.Label();
        Alternatif_Pengobatan = new widget.ComboBox();
        jLabel33 = new widget.Label();
        Kie_Alternatif_Pengobatan = new widget.ComboBox();
        label29 = new widget.Label();
        label30 = new widget.Label();
        jLabel34 = new widget.Label();
        Cara_Penggunaan_Obat = new widget.ComboBox();
        jLabel35 = new widget.Label();
        Kie_Cara_Penggunaan_Obat = new widget.ComboBox();
        label31 = new widget.Label();
        label32 = new widget.Label();
        jLabel36 = new widget.Label();
        Dosis_Jadwal_Obat = new widget.ComboBox();
        jLabel37 = new widget.Label();
        Kie_Dosis_Jadwal_Obat = new widget.ComboBox();
        label33 = new widget.Label();
        label34 = new widget.Label();
        jLabel38 = new widget.Label();
        Efek_Samping_Obat = new widget.ComboBox();
        jLabel39 = new widget.Label();
        Kie_Efek_Samping_Obat = new widget.ComboBox();
        label35 = new widget.Label();
        label36 = new widget.Label();
        jLabel40 = new widget.Label();
        Diet_Nutrisi = new widget.ComboBox();
        jLabel41 = new widget.Label();
        Kie_Diet_Nutrisi = new widget.ComboBox();
        label37 = new widget.Label();
        label38 = new widget.Label();
        jLabel42 = new widget.Label();
        Aktivitas_Mobilisasi = new widget.ComboBox();
        jLabel43 = new widget.Label();
        Kie_Aktivitas_Mobilisasi = new widget.ComboBox();
        label39 = new widget.Label();
        label40 = new widget.Label();
        jLabel44 = new widget.Label();
        Perawatan_Luka = new widget.ComboBox();
        jLabel45 = new widget.Label();
        Kie_Perawatan_Luka = new widget.ComboBox();
        label41 = new widget.Label();
        label42 = new widget.Label();
        jLabel46 = new widget.Label();
        Penggunaan_Alat_Medis = new widget.ComboBox();
        jLabel47 = new widget.Label();
        Kie_Penggunaan_Alat_Medis = new widget.ComboBox();
        label43 = new widget.Label();
        label44 = new widget.Label();
        jLabel48 = new widget.Label();
        Pencegahan_Komplikasi = new widget.ComboBox();
        jLabel49 = new widget.Label();
        Kie_Pencegahan_Komplikasi = new widget.ComboBox();
        label45 = new widget.Label();
        label46 = new widget.Label();
        jLabel50 = new widget.Label();
        Pencegahan_Resiko_Jatuh = new widget.ComboBox();
        jLabel51 = new widget.Label();
        Kie_Pencegahan_Resiko_Jatuh = new widget.ComboBox();
        label47 = new widget.Label();
        label48 = new widget.Label();
        jLabel52 = new widget.Label();
        Manajemen_Nyeri = new widget.ComboBox();
        jLabel53 = new widget.Label();
        Kie_Manajemen_Nyeri = new widget.ComboBox();
        label49 = new widget.Label();
        label50 = new widget.Label();
        jLabel54 = new widget.Label();
        Diagnosis_Penyakit = new widget.ComboBox();
        jLabel55 = new widget.Label();
        Kie_Diagnosis_Penyakit = new widget.ComboBox();
        label51 = new widget.Label();
        label52 = new widget.Label();
        jLabel56 = new widget.Label();
        Kebersihan_Diri = new widget.ComboBox();
        jLabel57 = new widget.Label();
        Kie_Kebersihan_Diri = new widget.ComboBox();
        label53 = new widget.Label();
        label54 = new widget.Label();
        jLabel58 = new widget.Label();
        Penundaan_Pelayanan = new widget.ComboBox();
        jLabel59 = new widget.Label();
        Kie_Penundaan_Pelayanan = new widget.ComboBox();
        label55 = new widget.Label();
        NipTanda = new widget.TextBox();
        NmTanda = new widget.TextBox();
        BtnDokter1 = new widget.Button();
        label56 = new widget.Label();
        NipRenMedis = new widget.TextBox();
        NmRenMedis = new widget.TextBox();
        BtnDokter2 = new widget.Button();
        label57 = new widget.Label();
        NipPengobatan = new widget.TextBox();
        NmPengobatan = new widget.TextBox();
        BtnDokter3 = new widget.Button();
        label58 = new widget.Label();
        NipProsesRawat = new widget.TextBox();
        NmProsesRawat = new widget.TextBox();
        BtnDokter4 = new widget.Button();
        label59 = new widget.Label();
        NipAltPengobatan = new widget.TextBox();
        NmAltPengobatan = new widget.TextBox();
        BtnDokter5 = new widget.Button();
        label60 = new widget.Label();
        NipCaraPenggunaan = new widget.TextBox();
        NmCaraPenggunaan = new widget.TextBox();
        BtnDokter6 = new widget.Button();
        label61 = new widget.Label();
        NipDosis = new widget.TextBox();
        NmDosis = new widget.TextBox();
        BtnDokter7 = new widget.Button();
        label62 = new widget.Label();
        NipEfek = new widget.TextBox();
        NmEfek = new widget.TextBox();
        BtnDokter8 = new widget.Button();
        label63 = new widget.Label();
        NipDiet = new widget.TextBox();
        NmDiet = new widget.TextBox();
        BtnDokter9 = new widget.Button();
        label64 = new widget.Label();
        NipMobilisasi = new widget.TextBox();
        NmMobilisasi = new widget.TextBox();
        BtnDokter10 = new widget.Button();
        label65 = new widget.Label();
        NipPerawatanLuka = new widget.TextBox();
        NmPerawatanLuka = new widget.TextBox();
        BtnDokter11 = new widget.Button();
        label66 = new widget.Label();
        NipPenggunaanAlatMedis = new widget.TextBox();
        NmPenggunaanAlatMedis = new widget.TextBox();
        BtnDokter12 = new widget.Button();
        label67 = new widget.Label();
        NipPencegahanKomplikasi = new widget.TextBox();
        NmPencegahanKomplikasi = new widget.TextBox();
        BtnDokter13 = new widget.Button();
        label68 = new widget.Label();
        NipManajemenNyeri = new widget.TextBox();
        NmManajemenNyeri = new widget.TextBox();
        BtnDokter14 = new widget.Button();
        label69 = new widget.Label();
        NipKebersihan = new widget.TextBox();
        NmKebersihan = new widget.TextBox();
        BtnDokter15 = new widget.Button();
        label70 = new widget.Label();
        NipPenundaan = new widget.TextBox();
        NmPenundaan = new widget.TextBox();
        BtnDokter16 = new widget.Button();
        label71 = new widget.Label();
        NipHambatan = new widget.TextBox();
        NmHambatan = new widget.TextBox();
        BtnDokter17 = new widget.Button();
        label72 = new widget.Label();
        NipResikoJatuh = new widget.TextBox();
        NmResikoJatuh = new widget.TextBox();
        BtnDokter18 = new widget.Button();
        label73 = new widget.Label();
        jLabel60 = new widget.Label();
        Kie_Lain_Lain = new widget.ComboBox();
        label74 = new widget.Label();
        NipLainLain = new widget.TextBox();
        NmLainLain = new widget.TextBox();
        BtnDokter19 = new widget.Button();
        Jam = new widget.ComboBox();
        Menit = new widget.ComboBox();
        Detik = new widget.ComboBox();
        ChkKejadian = new widget.CekBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Lain_Lain = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        Lain_Lain1 = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        Lain_Lain2 = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        Lain_Lain3 = new javax.swing.JTextArea();
        jLabel61 = new widget.Label();
        Kie_Lain_Lain1 = new widget.ComboBox();
        label75 = new widget.Label();
        NipLainLain1 = new widget.TextBox();
        NmLainLain1 = new widget.TextBox();
        BtnDokter20 = new widget.Button();
        jButton21 = new javax.swing.JButton();
        jLabel62 = new widget.Label();
        Kie_Lain_Lain2 = new widget.ComboBox();
        label76 = new widget.Label();
        NipLainLain2 = new widget.TextBox();
        NmLainLain2 = new widget.TextBox();
        BtnDokter21 = new widget.Button();
        jButton22 = new javax.swing.JButton();
        jLabel63 = new widget.Label();
        Kie_Lain_Lain3 = new widget.ComboBox();
        label77 = new widget.Label();
        NipLainLain3 = new widget.TextBox();
        NmLainLain3 = new widget.TextBox();
        BtnDokter22 = new widget.Button();
        jButton23 = new javax.swing.JButton();
        BtnDokter23 = new widget.Button();
        internalFrame3 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbObat = new widget.Table();
        panelGlass9 = new widget.panelisi();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        PanelAccor = new widget.PanelBiasa();
        ChkAccor = new widget.CekBox();
        FormPhoto = new widget.PanelBiasa();
        FormPass3 = new widget.PanelBiasa();
        btnAmbil = new widget.Button();
        BtnRefreshPhoto1 = new widget.Button();
        Scroll5 = new widget.ScrollPane();
        LoadHTML2 = new widget.editorpane();

        LoadHTML.setBorder(null);
        LoadHTML.setName("LoadHTML"); // NOI18N

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnCetakKie.setBackground(new java.awt.Color(255, 255, 254));
        MnCetakKie.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakKie.setForeground(new java.awt.Color(50, 50, 50));
        MnCetakKie.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakKie.setText("Cetak Kie");
        MnCetakKie.setName("MnCetakKie"); // NOI18N
        MnCetakKie.setPreferredSize(new java.awt.Dimension(220, 26));
        MnCetakKie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakKieActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnCetakKie);

        TanggalRegistrasi.setHighlighter(null);
        TanggalRegistrasi.setName("TanggalRegistrasi"); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Pelaksaanaan Informasi & Edukasi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 54));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan.setMnemonic('S');
        BtnSimpan.setText("Simpan");
        BtnSimpan.setToolTipText("Alt+S");
        BtnSimpan.setName("BtnSimpan"); // NOI18N
        BtnSimpan.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanActionPerformed(evt);
            }
        });
        BtnSimpan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSimpanKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnSimpan);

        BtnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBatal.setMnemonic('B');
        BtnBatal.setText("Baru");
        BtnBatal.setToolTipText("Alt+B");
        BtnBatal.setName("BtnBatal"); // NOI18N
        BtnBatal.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBatalActionPerformed(evt);
            }
        });
        BtnBatal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnBatalKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnBatal);

        BtnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapus.setMnemonic('H');
        BtnHapus.setText("Hapus");
        BtnHapus.setToolTipText("Alt+H");
        BtnHapus.setName("BtnHapus"); // NOI18N
        BtnHapus.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusActionPerformed(evt);
            }
        });
        BtnHapus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnHapusKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnHapus);

        BtnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnEdit.setMnemonic('G');
        BtnEdit.setText("Ganti");
        BtnEdit.setToolTipText("Alt+G");
        BtnEdit.setName("BtnEdit"); // NOI18N
        BtnEdit.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditActionPerformed(evt);
            }
        });
        BtnEdit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnEditKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnEdit);

        BtnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint.setMnemonic('T');
        BtnPrint.setText("Cetak");
        BtnPrint.setToolTipText("Alt+T");
        BtnPrint.setName("BtnPrint"); // NOI18N
        BtnPrint.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrintActionPerformed(evt);
            }
        });
        BtnPrint.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPrintKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnPrint);

        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setText("Semua");
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAllActionPerformed(evt);
            }
        });
        BtnAll.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAllKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnAll);

        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar.setMnemonic('K');
        BtnKeluar.setText("Keluar");
        BtnKeluar.setToolTipText("Alt+K");
        BtnKeluar.setName("BtnKeluar"); // NOI18N
        BtnKeluar.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluarActionPerformed(evt);
            }
        });
        BtnKeluar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKeluarKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnKeluar);

        internalFrame1.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        TabRawat.setBackground(new java.awt.Color(254, 255, 254));
        TabRawat.setForeground(new java.awt.Color(50, 50, 50));
        TabRawat.setName("TabRawat"); // NOI18N

        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        scrollInput.setName("scrollInput"); // NOI18N
        scrollInput.setPreferredSize(new java.awt.Dimension(102, 557));

        FormInput.setBackground(new java.awt.Color(255, 255, 255));
        FormInput.setBorder(null);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(870, 1200));
        FormInput.setLayout(null);

        jSeparator14.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator14.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator14.setName("jSeparator14"); // NOI18N
        FormInput.add(jSeparator14);
        jSeparator14.setBounds(0, 861, 880, 0);

        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N
        TNoRw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRwKeyPressed(evt);
            }
        });
        FormInput.add(TNoRw);
        TNoRw.setBounds(74, 10, 131, 23);

        TPasien.setEditable(false);
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(309, 10, 260, 23);

        TNoRM.setEditable(false);
        TNoRM.setHighlighter(null);
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(207, 10, 100, 23);

        jLabel8.setText("Tgl.Lahir :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(10, 40, 60, 23);

        TglLahir.setEditable(false);
        TglLahir.setHighlighter(null);
        TglLahir.setName("TglLahir"); // NOI18N
        FormInput.add(TglLahir);
        TglLahir.setBounds(70, 40, 80, 23);

        Jk.setEditable(false);
        Jk.setHighlighter(null);
        Jk.setName("Jk"); // NOI18N
        Jk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JkActionPerformed(evt);
            }
        });
        FormInput.add(Jk);
        Jk.setBounds(190, 40, 140, 23);

        jLabel10.setText("No.Rawat :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 10, 70, 23);

        label11.setText("Tanggal :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label11);
        label11.setBounds(330, 40, 57, 23);

        jLabel11.setText("J.K. :");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(150, 40, 30, 23);

        TglEdukasi.setForeground(new java.awt.Color(50, 70, 50));
        TglEdukasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-06-2026" }));
        TglEdukasi.setDisplayFormat("dd-MM-yyyy");
        TglEdukasi.setName("TglEdukasi"); // NOI18N
        TglEdukasi.setOpaque(false);
        TglEdukasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglEdukasiKeyPressed(evt);
            }
        });
        FormInput.add(TglEdukasi);
        TglEdukasi.setBounds(390, 40, 100, 23);

        BtnDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter.setMnemonic('2');
        BtnDokter.setToolTipText("Alt+2");
        BtnDokter.setName("BtnDokter"); // NOI18N
        BtnDokter.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokterActionPerformed(evt);
            }
        });
        BtnDokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnDokterKeyPressed(evt);
            }
        });
        FormInput.add(BtnDokter);
        BtnDokter.setBounds(960, 110, 28, 23);

        NmDiagnosa.setEditable(false);
        NmDiagnosa.setName("NmDiagnosa"); // NOI18N
        NmDiagnosa.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NmDiagnosa);
        NmDiagnosa.setBounds(750, 110, 210, 23);

        NipDiagnosa.setEditable(false);
        NipDiagnosa.setName("NipDiagnosa"); // NOI18N
        NipDiagnosa.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(NipDiagnosa);
        NipDiagnosa.setBounds(650, 110, 95, 23);

        label15.setText("Petugas :");
        label15.setName("label15"); // NOI18N
        label15.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label15);
        label15.setBounds(580, 110, 70, 23);

        jSeparator11.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator11.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator11.setName("jSeparator11"); // NOI18N
        FormInput.add(jSeparator11);
        jSeparator11.setBounds(0, 70, 880, 1);

        label16.setText("MATERI EDUKASI");
        label16.setName("label16"); // NOI18N
        label16.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label16);
        label16.setBounds(20, 80, 90, 23);

        label17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label17.setText("Hambatan Pelayanan");
        label17.setName("label17"); // NOI18N
        label17.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label17);
        label17.setBounds(40, 620, 170, 23);

        label18.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label18.setText(":");
        label18.setName("label18"); // NOI18N
        label18.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label18);
        label18.setBounds(190, 620, 10, 23);

        jLabel22.setText("Metode :");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput.add(jLabel22);
        jLabel22.setBounds(200, 620, 70, 23);

        Kie_Hambatan_Pelayanan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mengerti", "Cukup", "Belum", "-" }));
        Kie_Hambatan_Pelayanan.setName("Kie_Hambatan_Pelayanan"); // NOI18N
        Kie_Hambatan_Pelayanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Kie_Hambatan_PelayananActionPerformed(evt);
            }
        });
        Kie_Hambatan_Pelayanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Kie_Hambatan_PelayananKeyPressed(evt);
            }
        });
        FormInput.add(Kie_Hambatan_Pelayanan);
        Kie_Hambatan_Pelayanan.setBounds(450, 620, 130, 23);

        Hambatan_Pelayanan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Diskusi", "Demo", "Leaflet", "Video", "-" }));
        Hambatan_Pelayanan.setName("Hambatan_Pelayanan"); // NOI18N
        Hambatan_Pelayanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Hambatan_PelayananKeyPressed(evt);
            }
        });
        FormInput.add(Hambatan_Pelayanan);
        Hambatan_Pelayanan.setBounds(280, 620, 115, 23);

        jLabel23.setText("Hasil :");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(380, 620, 70, 23);

        label19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label19.setText("Tanda dan Gejala Penyakit");
        label19.setName("label19"); // NOI18N
        label19.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label19);
        label19.setBounds(40, 140, 180, 23);

        label20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label20.setText(":");
        label20.setName("label20"); // NOI18N
        label20.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label20);
        label20.setBounds(190, 140, 10, 23);

        jLabel24.setText("Metode :");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput.add(jLabel24);
        jLabel24.setBounds(200, 140, 70, 23);

        Tanda_Gejala_Penyakit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Diskusi", "Demo", "Leaflet", "Video", "-" }));
        Tanda_Gejala_Penyakit.setName("Tanda_Gejala_Penyakit"); // NOI18N
        Tanda_Gejala_Penyakit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tanda_Gejala_PenyakitKeyPressed(evt);
            }
        });
        FormInput.add(Tanda_Gejala_Penyakit);
        Tanda_Gejala_Penyakit.setBounds(280, 140, 115, 23);

        jLabel25.setText("Hasil :");
        jLabel25.setName("jLabel25"); // NOI18N
        FormInput.add(jLabel25);
        jLabel25.setBounds(380, 140, 70, 23);

        Kie_Tanda_Gejala_Penyakit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mengerti", "Cukup", "Belum", "-" }));
        Kie_Tanda_Gejala_Penyakit.setName("Kie_Tanda_Gejala_Penyakit"); // NOI18N
        Kie_Tanda_Gejala_Penyakit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Kie_Tanda_Gejala_PenyakitActionPerformed(evt);
            }
        });
        Kie_Tanda_Gejala_Penyakit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Kie_Tanda_Gejala_PenyakitKeyPressed(evt);
            }
        });
        FormInput.add(Kie_Tanda_Gejala_Penyakit);
        Kie_Tanda_Gejala_Penyakit.setBounds(450, 140, 130, 23);

        label21.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label21.setText("Rencana Tindakan Medis");
        label21.setName("label21"); // NOI18N
        label21.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label21);
        label21.setBounds(40, 170, 180, 23);

        label22.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label22.setText(":");
        label22.setName("label22"); // NOI18N
        label22.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label22);
        label22.setBounds(190, 170, 10, 23);

        jLabel26.setText("Metode :");
        jLabel26.setName("jLabel26"); // NOI18N
        FormInput.add(jLabel26);
        jLabel26.setBounds(200, 170, 70, 23);

        Ren_Tindakan_Medis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Diskusi", "Demo", "Leaflet", "Video", "-" }));
        Ren_Tindakan_Medis.setName("Ren_Tindakan_Medis"); // NOI18N
        Ren_Tindakan_Medis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Ren_Tindakan_MedisKeyPressed(evt);
            }
        });
        FormInput.add(Ren_Tindakan_Medis);
        Ren_Tindakan_Medis.setBounds(280, 170, 115, 23);

        jLabel27.setText("Hasil :");
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput.add(jLabel27);
        jLabel27.setBounds(380, 170, 70, 23);

        Kie_Ren_Tindakan_Medis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mengerti", "Cukup", "Belum", "-" }));
        Kie_Ren_Tindakan_Medis.setName("Kie_Ren_Tindakan_Medis"); // NOI18N
        Kie_Ren_Tindakan_Medis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Kie_Ren_Tindakan_MedisActionPerformed(evt);
            }
        });
        Kie_Ren_Tindakan_Medis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Kie_Ren_Tindakan_MedisKeyPressed(evt);
            }
        });
        FormInput.add(Kie_Ren_Tindakan_Medis);
        Kie_Ren_Tindakan_Medis.setBounds(450, 170, 130, 23);

        label23.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label23.setText("Pengobatan yang Diberikan");
        label23.setName("label23"); // NOI18N
        label23.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label23);
        label23.setBounds(40, 200, 180, 23);

        label24.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label24.setText(":");
        label24.setName("label24"); // NOI18N
        label24.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label24);
        label24.setBounds(190, 200, 10, 23);

        jLabel28.setText("Metode :");
        jLabel28.setName("jLabel28"); // NOI18N
        FormInput.add(jLabel28);
        jLabel28.setBounds(200, 200, 70, 23);

        Pengobatan_Diberikan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Diskusi", "Demo", "Leaflet", "Video", "-" }));
        Pengobatan_Diberikan.setName("Pengobatan_Diberikan"); // NOI18N
        Pengobatan_Diberikan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Pengobatan_DiberikanKeyPressed(evt);
            }
        });
        FormInput.add(Pengobatan_Diberikan);
        Pengobatan_Diberikan.setBounds(280, 200, 115, 23);

        jLabel29.setText("Hasil :");
        jLabel29.setName("jLabel29"); // NOI18N
        FormInput.add(jLabel29);
        jLabel29.setBounds(380, 200, 70, 23);

        Kie_Pengobatan_Diberikan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mengerti", "Cukup", "Belum", "-" }));
        Kie_Pengobatan_Diberikan.setName("Kie_Pengobatan_Diberikan"); // NOI18N
        Kie_Pengobatan_Diberikan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Kie_Pengobatan_DiberikanActionPerformed(evt);
            }
        });
        Kie_Pengobatan_Diberikan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Kie_Pengobatan_DiberikanKeyPressed(evt);
            }
        });
        FormInput.add(Kie_Pengobatan_Diberikan);
        Kie_Pengobatan_Diberikan.setBounds(450, 200, 130, 23);

        label25.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label25.setText("Proses Perawatan");
        label25.setName("label25"); // NOI18N
        label25.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label25);
        label25.setBounds(40, 230, 170, 23);

        label26.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label26.setText(":");
        label26.setName("label26"); // NOI18N
        label26.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label26);
        label26.setBounds(190, 230, 10, 23);

        jLabel30.setText("Metode :");
        jLabel30.setName("jLabel30"); // NOI18N
        FormInput.add(jLabel30);
        jLabel30.setBounds(200, 230, 70, 23);

        Proses_Perawatan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Diskusi", "Demo", "Leaflet", "Video", "-" }));
        Proses_Perawatan.setName("Proses_Perawatan"); // NOI18N
        Proses_Perawatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Proses_PerawatanKeyPressed(evt);
            }
        });
        FormInput.add(Proses_Perawatan);
        Proses_Perawatan.setBounds(280, 230, 115, 23);

        jLabel31.setText("Hasil :");
        jLabel31.setName("jLabel31"); // NOI18N
        FormInput.add(jLabel31);
        jLabel31.setBounds(380, 230, 70, 23);

        Kie_Proses_Perawatan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mengerti", "Cukup", "Belum", "-" }));
        Kie_Proses_Perawatan.setName("Kie_Proses_Perawatan"); // NOI18N
        Kie_Proses_Perawatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Kie_Proses_PerawatanActionPerformed(evt);
            }
        });
        Kie_Proses_Perawatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Kie_Proses_PerawatanKeyPressed(evt);
            }
        });
        FormInput.add(Kie_Proses_Perawatan);
        Kie_Proses_Perawatan.setBounds(450, 230, 130, 23);

        label27.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label27.setText("Alternatif Pengobatan");
        label27.setName("label27"); // NOI18N
        label27.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label27);
        label27.setBounds(40, 260, 170, 23);

        label28.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label28.setText(":");
        label28.setName("label28"); // NOI18N
        label28.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label28);
        label28.setBounds(190, 260, 10, 23);

        jLabel32.setText("Metode :");
        jLabel32.setName("jLabel32"); // NOI18N
        FormInput.add(jLabel32);
        jLabel32.setBounds(200, 260, 70, 23);

        Alternatif_Pengobatan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Diskusi", "Demo", "Leaflet", "Video", "-" }));
        Alternatif_Pengobatan.setName("Alternatif_Pengobatan"); // NOI18N
        Alternatif_Pengobatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Alternatif_PengobatanKeyPressed(evt);
            }
        });
        FormInput.add(Alternatif_Pengobatan);
        Alternatif_Pengobatan.setBounds(280, 260, 115, 23);

        jLabel33.setText("Hasil :");
        jLabel33.setName("jLabel33"); // NOI18N
        FormInput.add(jLabel33);
        jLabel33.setBounds(380, 260, 70, 23);

        Kie_Alternatif_Pengobatan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mengerti", "Cukup", "Belum", "-" }));
        Kie_Alternatif_Pengobatan.setName("Kie_Alternatif_Pengobatan"); // NOI18N
        Kie_Alternatif_Pengobatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Kie_Alternatif_PengobatanActionPerformed(evt);
            }
        });
        Kie_Alternatif_Pengobatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Kie_Alternatif_PengobatanKeyPressed(evt);
            }
        });
        FormInput.add(Kie_Alternatif_Pengobatan);
        Kie_Alternatif_Pengobatan.setBounds(450, 260, 130, 23);

        label29.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label29.setText("Cara Penggunaan Obat");
        label29.setName("label29"); // NOI18N
        label29.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label29);
        label29.setBounds(40, 290, 170, 23);

        label30.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label30.setText(":");
        label30.setName("label30"); // NOI18N
        label30.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label30);
        label30.setBounds(190, 290, 10, 23);

        jLabel34.setText("Metode :");
        jLabel34.setName("jLabel34"); // NOI18N
        FormInput.add(jLabel34);
        jLabel34.setBounds(200, 290, 70, 23);

        Cara_Penggunaan_Obat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Diskusi", "Demo", "Leaflet", "Video", "-" }));
        Cara_Penggunaan_Obat.setName("Cara_Penggunaan_Obat"); // NOI18N
        Cara_Penggunaan_Obat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Cara_Penggunaan_ObatKeyPressed(evt);
            }
        });
        FormInput.add(Cara_Penggunaan_Obat);
        Cara_Penggunaan_Obat.setBounds(280, 290, 115, 23);

        jLabel35.setText("Hasil :");
        jLabel35.setName("jLabel35"); // NOI18N
        FormInput.add(jLabel35);
        jLabel35.setBounds(380, 290, 70, 23);

        Kie_Cara_Penggunaan_Obat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mengerti", "Cukup", "Belum", "-" }));
        Kie_Cara_Penggunaan_Obat.setName("Kie_Cara_Penggunaan_Obat"); // NOI18N
        Kie_Cara_Penggunaan_Obat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Kie_Cara_Penggunaan_ObatActionPerformed(evt);
            }
        });
        Kie_Cara_Penggunaan_Obat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Kie_Cara_Penggunaan_ObatKeyPressed(evt);
            }
        });
        FormInput.add(Kie_Cara_Penggunaan_Obat);
        Kie_Cara_Penggunaan_Obat.setBounds(450, 290, 130, 23);

        label31.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label31.setText("Dosis dan Jadwal Obat");
        label31.setName("label31"); // NOI18N
        label31.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label31);
        label31.setBounds(40, 320, 170, 23);

        label32.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label32.setText(":");
        label32.setName("label32"); // NOI18N
        label32.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label32);
        label32.setBounds(190, 320, 10, 23);

        jLabel36.setText("Metode :");
        jLabel36.setName("jLabel36"); // NOI18N
        FormInput.add(jLabel36);
        jLabel36.setBounds(200, 320, 70, 23);

        Dosis_Jadwal_Obat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Diskusi", "Demo", "Leaflet", "Video", "-" }));
        Dosis_Jadwal_Obat.setName("Dosis_Jadwal_Obat"); // NOI18N
        Dosis_Jadwal_Obat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Dosis_Jadwal_ObatKeyPressed(evt);
            }
        });
        FormInput.add(Dosis_Jadwal_Obat);
        Dosis_Jadwal_Obat.setBounds(280, 320, 115, 23);

        jLabel37.setText("Hasil :");
        jLabel37.setName("jLabel37"); // NOI18N
        FormInput.add(jLabel37);
        jLabel37.setBounds(380, 320, 70, 23);

        Kie_Dosis_Jadwal_Obat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mengerti", "Cukup", "Belum", "-" }));
        Kie_Dosis_Jadwal_Obat.setName("Kie_Dosis_Jadwal_Obat"); // NOI18N
        Kie_Dosis_Jadwal_Obat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Kie_Dosis_Jadwal_ObatActionPerformed(evt);
            }
        });
        Kie_Dosis_Jadwal_Obat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Kie_Dosis_Jadwal_ObatKeyPressed(evt);
            }
        });
        FormInput.add(Kie_Dosis_Jadwal_Obat);
        Kie_Dosis_Jadwal_Obat.setBounds(450, 320, 130, 23);

        label33.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label33.setText("Efek Samping Obat");
        label33.setName("label33"); // NOI18N
        label33.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label33);
        label33.setBounds(40, 350, 170, 23);

        label34.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label34.setText(":");
        label34.setName("label34"); // NOI18N
        label34.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label34);
        label34.setBounds(190, 350, 10, 23);

        jLabel38.setText("Metode :");
        jLabel38.setName("jLabel38"); // NOI18N
        FormInput.add(jLabel38);
        jLabel38.setBounds(200, 350, 70, 23);

        Efek_Samping_Obat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Diskusi", "Demo", "Leaflet", "Video", "-" }));
        Efek_Samping_Obat.setName("Efek_Samping_Obat"); // NOI18N
        Efek_Samping_Obat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Efek_Samping_ObatKeyPressed(evt);
            }
        });
        FormInput.add(Efek_Samping_Obat);
        Efek_Samping_Obat.setBounds(280, 350, 115, 23);

        jLabel39.setText("Hasil :");
        jLabel39.setName("jLabel39"); // NOI18N
        FormInput.add(jLabel39);
        jLabel39.setBounds(380, 350, 70, 23);

        Kie_Efek_Samping_Obat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mengerti", "Cukup", "Belum", "-" }));
        Kie_Efek_Samping_Obat.setName("Kie_Efek_Samping_Obat"); // NOI18N
        Kie_Efek_Samping_Obat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Kie_Efek_Samping_ObatActionPerformed(evt);
            }
        });
        Kie_Efek_Samping_Obat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Kie_Efek_Samping_ObatKeyPressed(evt);
            }
        });
        FormInput.add(Kie_Efek_Samping_Obat);
        Kie_Efek_Samping_Obat.setBounds(450, 350, 130, 23);

        label35.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label35.setText("Diet / Nutrisi");
        label35.setName("label35"); // NOI18N
        label35.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label35);
        label35.setBounds(40, 380, 170, 23);

        label36.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label36.setText(":");
        label36.setName("label36"); // NOI18N
        label36.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label36);
        label36.setBounds(190, 380, 10, 23);

        jLabel40.setText("Metode :");
        jLabel40.setName("jLabel40"); // NOI18N
        FormInput.add(jLabel40);
        jLabel40.setBounds(200, 380, 70, 23);

        Diet_Nutrisi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Diskusi", "Demo", "Leaflet", "Video", "-" }));
        Diet_Nutrisi.setName("Diet_Nutrisi"); // NOI18N
        Diet_Nutrisi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Diet_NutrisiKeyPressed(evt);
            }
        });
        FormInput.add(Diet_Nutrisi);
        Diet_Nutrisi.setBounds(280, 380, 115, 23);

        jLabel41.setText("Hasil :");
        jLabel41.setName("jLabel41"); // NOI18N
        FormInput.add(jLabel41);
        jLabel41.setBounds(380, 380, 70, 23);

        Kie_Diet_Nutrisi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mengerti", "Cukup", "Belum", "-" }));
        Kie_Diet_Nutrisi.setName("Kie_Diet_Nutrisi"); // NOI18N
        Kie_Diet_Nutrisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Kie_Diet_NutrisiActionPerformed(evt);
            }
        });
        Kie_Diet_Nutrisi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Kie_Diet_NutrisiKeyPressed(evt);
            }
        });
        FormInput.add(Kie_Diet_Nutrisi);
        Kie_Diet_Nutrisi.setBounds(450, 380, 130, 23);

        label37.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label37.setText("Aktivitas / Mobilisisasi");
        label37.setName("label37"); // NOI18N
        label37.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label37);
        label37.setBounds(40, 410, 170, 23);

        label38.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label38.setText(":");
        label38.setName("label38"); // NOI18N
        label38.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label38);
        label38.setBounds(190, 410, 10, 23);

        jLabel42.setText("Metode :");
        jLabel42.setName("jLabel42"); // NOI18N
        FormInput.add(jLabel42);
        jLabel42.setBounds(200, 410, 70, 23);

        Aktivitas_Mobilisasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Diskusi", "Demo", "Leaflet", "Video", "-" }));
        Aktivitas_Mobilisasi.setName("Aktivitas_Mobilisasi"); // NOI18N
        Aktivitas_Mobilisasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Aktivitas_MobilisasiKeyPressed(evt);
            }
        });
        FormInput.add(Aktivitas_Mobilisasi);
        Aktivitas_Mobilisasi.setBounds(280, 410, 115, 23);

        jLabel43.setText("Hasil :");
        jLabel43.setName("jLabel43"); // NOI18N
        FormInput.add(jLabel43);
        jLabel43.setBounds(380, 410, 70, 23);

        Kie_Aktivitas_Mobilisasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mengerti", "Cukup", "Belum", "-" }));
        Kie_Aktivitas_Mobilisasi.setName("Kie_Aktivitas_Mobilisasi"); // NOI18N
        Kie_Aktivitas_Mobilisasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Kie_Aktivitas_MobilisasiActionPerformed(evt);
            }
        });
        Kie_Aktivitas_Mobilisasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Kie_Aktivitas_MobilisasiKeyPressed(evt);
            }
        });
        FormInput.add(Kie_Aktivitas_Mobilisasi);
        Kie_Aktivitas_Mobilisasi.setBounds(450, 410, 130, 23);

        label39.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label39.setText("Perawatan Luka");
        label39.setName("label39"); // NOI18N
        label39.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label39);
        label39.setBounds(40, 440, 170, 23);

        label40.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label40.setText(":");
        label40.setName("label40"); // NOI18N
        label40.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label40);
        label40.setBounds(190, 440, 10, 23);

        jLabel44.setText("Metode :");
        jLabel44.setName("jLabel44"); // NOI18N
        FormInput.add(jLabel44);
        jLabel44.setBounds(200, 440, 70, 23);

        Perawatan_Luka.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Diskusi", "Demo", "Leaflet", "Video", "-" }));
        Perawatan_Luka.setName("Perawatan_Luka"); // NOI18N
        Perawatan_Luka.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Perawatan_LukaKeyPressed(evt);
            }
        });
        FormInput.add(Perawatan_Luka);
        Perawatan_Luka.setBounds(280, 440, 115, 23);

        jLabel45.setText("Hasil :");
        jLabel45.setName("jLabel45"); // NOI18N
        FormInput.add(jLabel45);
        jLabel45.setBounds(380, 440, 70, 23);

        Kie_Perawatan_Luka.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mengerti", "Cukup", "Belum", "-" }));
        Kie_Perawatan_Luka.setName("Kie_Perawatan_Luka"); // NOI18N
        Kie_Perawatan_Luka.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Kie_Perawatan_LukaActionPerformed(evt);
            }
        });
        Kie_Perawatan_Luka.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Kie_Perawatan_LukaKeyPressed(evt);
            }
        });
        FormInput.add(Kie_Perawatan_Luka);
        Kie_Perawatan_Luka.setBounds(450, 440, 130, 23);

        label41.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label41.setText("Pengunaan Alat Medis");
        label41.setName("label41"); // NOI18N
        label41.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label41);
        label41.setBounds(40, 470, 170, 23);

        label42.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label42.setText(":");
        label42.setName("label42"); // NOI18N
        label42.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label42);
        label42.setBounds(190, 470, 10, 23);

        jLabel46.setText("Metode :");
        jLabel46.setName("jLabel46"); // NOI18N
        FormInput.add(jLabel46);
        jLabel46.setBounds(200, 470, 70, 23);

        Penggunaan_Alat_Medis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Diskusi", "Demo", "Leaflet", "Video", "-" }));
        Penggunaan_Alat_Medis.setName("Penggunaan_Alat_Medis"); // NOI18N
        Penggunaan_Alat_Medis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Penggunaan_Alat_MedisKeyPressed(evt);
            }
        });
        FormInput.add(Penggunaan_Alat_Medis);
        Penggunaan_Alat_Medis.setBounds(280, 470, 115, 23);

        jLabel47.setText("Hasil :");
        jLabel47.setName("jLabel47"); // NOI18N
        FormInput.add(jLabel47);
        jLabel47.setBounds(380, 470, 70, 23);

        Kie_Penggunaan_Alat_Medis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mengerti", "Cukup", "Belum", "-" }));
        Kie_Penggunaan_Alat_Medis.setName("Kie_Penggunaan_Alat_Medis"); // NOI18N
        Kie_Penggunaan_Alat_Medis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Kie_Penggunaan_Alat_MedisActionPerformed(evt);
            }
        });
        Kie_Penggunaan_Alat_Medis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Kie_Penggunaan_Alat_MedisKeyPressed(evt);
            }
        });
        FormInput.add(Kie_Penggunaan_Alat_Medis);
        Kie_Penggunaan_Alat_Medis.setBounds(450, 470, 130, 23);

        label43.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label43.setText("Pencegahan Komplikasi");
        label43.setName("label43"); // NOI18N
        label43.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label43);
        label43.setBounds(40, 500, 170, 23);

        label44.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label44.setText(":");
        label44.setName("label44"); // NOI18N
        label44.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label44);
        label44.setBounds(190, 500, 10, 23);

        jLabel48.setText("Metode :");
        jLabel48.setName("jLabel48"); // NOI18N
        FormInput.add(jLabel48);
        jLabel48.setBounds(200, 500, 70, 23);

        Pencegahan_Komplikasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Diskusi", "Demo", "Leaflet", "Video", "-" }));
        Pencegahan_Komplikasi.setName("Pencegahan_Komplikasi"); // NOI18N
        Pencegahan_Komplikasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Pencegahan_KomplikasiKeyPressed(evt);
            }
        });
        FormInput.add(Pencegahan_Komplikasi);
        Pencegahan_Komplikasi.setBounds(280, 500, 115, 23);

        jLabel49.setText("Hasil :");
        jLabel49.setName("jLabel49"); // NOI18N
        FormInput.add(jLabel49);
        jLabel49.setBounds(380, 500, 70, 23);

        Kie_Pencegahan_Komplikasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mengerti", "Cukup", "Belum", "-" }));
        Kie_Pencegahan_Komplikasi.setName("Kie_Pencegahan_Komplikasi"); // NOI18N
        Kie_Pencegahan_Komplikasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Kie_Pencegahan_KomplikasiActionPerformed(evt);
            }
        });
        Kie_Pencegahan_Komplikasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Kie_Pencegahan_KomplikasiKeyPressed(evt);
            }
        });
        FormInput.add(Kie_Pencegahan_Komplikasi);
        Kie_Pencegahan_Komplikasi.setBounds(450, 500, 130, 23);

        label45.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label45.setText("Lain Lain");
        label45.setName("label45"); // NOI18N
        label45.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label45);
        label45.setBounds(40, 680, 90, 23);

        label46.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label46.setText(":");
        label46.setName("label46"); // NOI18N
        label46.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label46);
        label46.setBounds(190, 650, 10, 23);

        jLabel50.setText("Metode :");
        jLabel50.setName("jLabel50"); // NOI18N
        FormInput.add(jLabel50);
        jLabel50.setBounds(200, 650, 70, 23);

        Pencegahan_Resiko_Jatuh.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Diskusi", "Demo", "Leaflet", "Video", "-" }));
        Pencegahan_Resiko_Jatuh.setName("Pencegahan_Resiko_Jatuh"); // NOI18N
        Pencegahan_Resiko_Jatuh.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Pencegahan_Resiko_JatuhKeyPressed(evt);
            }
        });
        FormInput.add(Pencegahan_Resiko_Jatuh);
        Pencegahan_Resiko_Jatuh.setBounds(280, 650, 115, 23);

        jLabel51.setText("Hasil :");
        jLabel51.setName("jLabel51"); // NOI18N
        FormInput.add(jLabel51);
        jLabel51.setBounds(380, 650, 70, 23);

        Kie_Pencegahan_Resiko_Jatuh.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mengerti", "Cukup", "Belum", "-" }));
        Kie_Pencegahan_Resiko_Jatuh.setName("Kie_Pencegahan_Resiko_Jatuh"); // NOI18N
        Kie_Pencegahan_Resiko_Jatuh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Kie_Pencegahan_Resiko_JatuhActionPerformed(evt);
            }
        });
        Kie_Pencegahan_Resiko_Jatuh.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Kie_Pencegahan_Resiko_JatuhKeyPressed(evt);
            }
        });
        FormInput.add(Kie_Pencegahan_Resiko_Jatuh);
        Kie_Pencegahan_Resiko_Jatuh.setBounds(450, 650, 130, 23);

        label47.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label47.setText("Manajemen Nyeri");
        label47.setName("label47"); // NOI18N
        label47.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label47);
        label47.setBounds(40, 530, 170, 23);

        label48.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label48.setText(":");
        label48.setName("label48"); // NOI18N
        label48.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label48);
        label48.setBounds(190, 530, 10, 23);

        jLabel52.setText("Metode :");
        jLabel52.setName("jLabel52"); // NOI18N
        FormInput.add(jLabel52);
        jLabel52.setBounds(200, 530, 70, 23);

        Manajemen_Nyeri.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Diskusi", "Demo", "Leaflet", "Video", "-" }));
        Manajemen_Nyeri.setName("Manajemen_Nyeri"); // NOI18N
        Manajemen_Nyeri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Manajemen_NyeriKeyPressed(evt);
            }
        });
        FormInput.add(Manajemen_Nyeri);
        Manajemen_Nyeri.setBounds(280, 530, 115, 23);

        jLabel53.setText("Hasil :");
        jLabel53.setName("jLabel53"); // NOI18N
        FormInput.add(jLabel53);
        jLabel53.setBounds(380, 530, 70, 23);

        Kie_Manajemen_Nyeri.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mengerti", "Cukup", "Belum", "-" }));
        Kie_Manajemen_Nyeri.setName("Kie_Manajemen_Nyeri"); // NOI18N
        Kie_Manajemen_Nyeri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Kie_Manajemen_NyeriActionPerformed(evt);
            }
        });
        Kie_Manajemen_Nyeri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Kie_Manajemen_NyeriKeyPressed(evt);
            }
        });
        FormInput.add(Kie_Manajemen_Nyeri);
        Kie_Manajemen_Nyeri.setBounds(450, 530, 130, 23);

        label49.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label49.setText("Diagnosis Penyakit ");
        label49.setName("label49"); // NOI18N
        label49.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label49);
        label49.setBounds(40, 110, 100, 23);

        label50.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label50.setText(":");
        label50.setName("label50"); // NOI18N
        label50.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label50);
        label50.setBounds(190, 110, 10, 23);

        jLabel54.setText("Metode :");
        jLabel54.setName("jLabel54"); // NOI18N
        FormInput.add(jLabel54);
        jLabel54.setBounds(200, 110, 70, 23);

        Diagnosis_Penyakit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Diskusi", "Demo", "Leaflet", "Video", "-" }));
        Diagnosis_Penyakit.setName("Diagnosis_Penyakit"); // NOI18N
        Diagnosis_Penyakit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Diagnosis_PenyakitKeyPressed(evt);
            }
        });
        FormInput.add(Diagnosis_Penyakit);
        Diagnosis_Penyakit.setBounds(280, 110, 115, 23);

        jLabel55.setText("Hasil :");
        jLabel55.setName("jLabel55"); // NOI18N
        FormInput.add(jLabel55);
        jLabel55.setBounds(380, 110, 70, 23);

        Kie_Diagnosis_Penyakit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mengerti", "Cukup", "Belum", "-" }));
        Kie_Diagnosis_Penyakit.setName("Kie_Diagnosis_Penyakit"); // NOI18N
        Kie_Diagnosis_Penyakit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Kie_Diagnosis_PenyakitActionPerformed(evt);
            }
        });
        Kie_Diagnosis_Penyakit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Kie_Diagnosis_PenyakitKeyPressed(evt);
            }
        });
        FormInput.add(Kie_Diagnosis_Penyakit);
        Kie_Diagnosis_Penyakit.setBounds(450, 110, 130, 23);

        label51.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label51.setText("Kebersihan Diri / Hand Hygiene");
        label51.setName("label51"); // NOI18N
        label51.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label51);
        label51.setBounds(40, 560, 170, 23);

        label52.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label52.setText(":");
        label52.setName("label52"); // NOI18N
        label52.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label52);
        label52.setBounds(190, 560, 10, 23);

        jLabel56.setText("Metode :");
        jLabel56.setName("jLabel56"); // NOI18N
        FormInput.add(jLabel56);
        jLabel56.setBounds(200, 560, 70, 23);

        Kebersihan_Diri.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Diskusi", "Demo", "Leaflet", "Video", "-" }));
        Kebersihan_Diri.setName("Kebersihan_Diri"); // NOI18N
        Kebersihan_Diri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Kebersihan_DiriKeyPressed(evt);
            }
        });
        FormInput.add(Kebersihan_Diri);
        Kebersihan_Diri.setBounds(280, 560, 115, 23);

        jLabel57.setText("Hasil :");
        jLabel57.setName("jLabel57"); // NOI18N
        FormInput.add(jLabel57);
        jLabel57.setBounds(380, 560, 70, 23);

        Kie_Kebersihan_Diri.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mengerti", "Cukup", "Belum", "-" }));
        Kie_Kebersihan_Diri.setName("Kie_Kebersihan_Diri"); // NOI18N
        Kie_Kebersihan_Diri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Kie_Kebersihan_DiriActionPerformed(evt);
            }
        });
        Kie_Kebersihan_Diri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Kie_Kebersihan_DiriKeyPressed(evt);
            }
        });
        FormInput.add(Kie_Kebersihan_Diri);
        Kie_Kebersihan_Diri.setBounds(450, 560, 130, 23);

        label53.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label53.setText("Penundaan Pelayanan");
        label53.setName("label53"); // NOI18N
        label53.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label53);
        label53.setBounds(40, 590, 170, 23);

        label54.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label54.setText(":");
        label54.setName("label54"); // NOI18N
        label54.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label54);
        label54.setBounds(190, 590, 10, 23);

        jLabel58.setText("Metode :");
        jLabel58.setName("jLabel58"); // NOI18N
        FormInput.add(jLabel58);
        jLabel58.setBounds(200, 590, 70, 23);

        Penundaan_Pelayanan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Diskusi", "Demo", "Leaflet", "Video", "-" }));
        Penundaan_Pelayanan.setName("Penundaan_Pelayanan"); // NOI18N
        Penundaan_Pelayanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Penundaan_PelayananKeyPressed(evt);
            }
        });
        FormInput.add(Penundaan_Pelayanan);
        Penundaan_Pelayanan.setBounds(280, 590, 115, 23);

        jLabel59.setText("Hasil :");
        jLabel59.setName("jLabel59"); // NOI18N
        FormInput.add(jLabel59);
        jLabel59.setBounds(380, 590, 70, 23);

        Kie_Penundaan_Pelayanan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mengerti", "Cukup", "Belum", "-" }));
        Kie_Penundaan_Pelayanan.setName("Kie_Penundaan_Pelayanan"); // NOI18N
        Kie_Penundaan_Pelayanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Kie_Penundaan_PelayananActionPerformed(evt);
            }
        });
        Kie_Penundaan_Pelayanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Kie_Penundaan_PelayananKeyPressed(evt);
            }
        });
        FormInput.add(Kie_Penundaan_Pelayanan);
        Kie_Penundaan_Pelayanan.setBounds(450, 590, 130, 23);

        label55.setText("Petugas :");
        label55.setName("label55"); // NOI18N
        label55.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label55);
        label55.setBounds(580, 140, 70, 23);

        NipTanda.setEditable(false);
        NipTanda.setName("NipTanda"); // NOI18N
        NipTanda.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(NipTanda);
        NipTanda.setBounds(650, 140, 95, 23);

        NmTanda.setEditable(false);
        NmTanda.setName("NmTanda"); // NOI18N
        NmTanda.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NmTanda);
        NmTanda.setBounds(750, 140, 210, 23);

        BtnDokter1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter1.setMnemonic('2');
        BtnDokter1.setToolTipText("Alt+2");
        BtnDokter1.setName("BtnDokter1"); // NOI18N
        BtnDokter1.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokter1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokter1ActionPerformed(evt);
            }
        });
        BtnDokter1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnDokter1KeyPressed(evt);
            }
        });
        FormInput.add(BtnDokter1);
        BtnDokter1.setBounds(960, 140, 28, 23);

        label56.setText("Petugas :");
        label56.setName("label56"); // NOI18N
        label56.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label56);
        label56.setBounds(580, 170, 70, 23);

        NipRenMedis.setEditable(false);
        NipRenMedis.setName("NipRenMedis"); // NOI18N
        NipRenMedis.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(NipRenMedis);
        NipRenMedis.setBounds(650, 170, 95, 23);

        NmRenMedis.setEditable(false);
        NmRenMedis.setName("NmRenMedis"); // NOI18N
        NmRenMedis.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NmRenMedis);
        NmRenMedis.setBounds(750, 170, 210, 23);

        BtnDokter2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter2.setMnemonic('2');
        BtnDokter2.setToolTipText("Alt+2");
        BtnDokter2.setName("BtnDokter2"); // NOI18N
        BtnDokter2.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokter2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokter2ActionPerformed(evt);
            }
        });
        BtnDokter2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnDokter2KeyPressed(evt);
            }
        });
        FormInput.add(BtnDokter2);
        BtnDokter2.setBounds(960, 170, 28, 23);

        label57.setText("Petugas :");
        label57.setName("label57"); // NOI18N
        label57.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label57);
        label57.setBounds(580, 200, 70, 23);

        NipPengobatan.setEditable(false);
        NipPengobatan.setName("NipPengobatan"); // NOI18N
        NipPengobatan.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(NipPengobatan);
        NipPengobatan.setBounds(650, 200, 95, 23);

        NmPengobatan.setEditable(false);
        NmPengobatan.setName("NmPengobatan"); // NOI18N
        NmPengobatan.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NmPengobatan);
        NmPengobatan.setBounds(750, 200, 210, 23);

        BtnDokter3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter3.setMnemonic('2');
        BtnDokter3.setToolTipText("Alt+2");
        BtnDokter3.setName("BtnDokter3"); // NOI18N
        BtnDokter3.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokter3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokter3ActionPerformed(evt);
            }
        });
        BtnDokter3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnDokter3KeyPressed(evt);
            }
        });
        FormInput.add(BtnDokter3);
        BtnDokter3.setBounds(960, 200, 28, 23);

        label58.setText("Petugas :");
        label58.setName("label58"); // NOI18N
        label58.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label58);
        label58.setBounds(580, 230, 70, 23);

        NipProsesRawat.setEditable(false);
        NipProsesRawat.setName("NipProsesRawat"); // NOI18N
        NipProsesRawat.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(NipProsesRawat);
        NipProsesRawat.setBounds(650, 230, 95, 23);

        NmProsesRawat.setEditable(false);
        NmProsesRawat.setName("NmProsesRawat"); // NOI18N
        NmProsesRawat.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NmProsesRawat);
        NmProsesRawat.setBounds(750, 230, 210, 23);

        BtnDokter4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter4.setMnemonic('2');
        BtnDokter4.setToolTipText("Alt+2");
        BtnDokter4.setName("BtnDokter4"); // NOI18N
        BtnDokter4.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokter4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokter4ActionPerformed(evt);
            }
        });
        BtnDokter4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnDokter4KeyPressed(evt);
            }
        });
        FormInput.add(BtnDokter4);
        BtnDokter4.setBounds(960, 230, 28, 23);

        label59.setText("Petugas :");
        label59.setName("label59"); // NOI18N
        label59.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label59);
        label59.setBounds(580, 260, 70, 23);

        NipAltPengobatan.setEditable(false);
        NipAltPengobatan.setName("NipAltPengobatan"); // NOI18N
        NipAltPengobatan.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(NipAltPengobatan);
        NipAltPengobatan.setBounds(650, 260, 95, 23);

        NmAltPengobatan.setEditable(false);
        NmAltPengobatan.setName("NmAltPengobatan"); // NOI18N
        NmAltPengobatan.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NmAltPengobatan);
        NmAltPengobatan.setBounds(750, 260, 210, 23);

        BtnDokter5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter5.setMnemonic('2');
        BtnDokter5.setToolTipText("Alt+2");
        BtnDokter5.setName("BtnDokter5"); // NOI18N
        BtnDokter5.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokter5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokter5ActionPerformed(evt);
            }
        });
        BtnDokter5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnDokter5KeyPressed(evt);
            }
        });
        FormInput.add(BtnDokter5);
        BtnDokter5.setBounds(960, 260, 28, 23);

        label60.setText("Petugas :");
        label60.setName("label60"); // NOI18N
        label60.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label60);
        label60.setBounds(580, 290, 70, 23);

        NipCaraPenggunaan.setEditable(false);
        NipCaraPenggunaan.setName("NipCaraPenggunaan"); // NOI18N
        NipCaraPenggunaan.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(NipCaraPenggunaan);
        NipCaraPenggunaan.setBounds(650, 290, 95, 23);

        NmCaraPenggunaan.setEditable(false);
        NmCaraPenggunaan.setName("NmCaraPenggunaan"); // NOI18N
        NmCaraPenggunaan.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NmCaraPenggunaan);
        NmCaraPenggunaan.setBounds(750, 290, 210, 23);

        BtnDokter6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter6.setMnemonic('2');
        BtnDokter6.setToolTipText("Alt+2");
        BtnDokter6.setName("BtnDokter6"); // NOI18N
        BtnDokter6.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokter6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokter6ActionPerformed(evt);
            }
        });
        BtnDokter6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnDokter6KeyPressed(evt);
            }
        });
        FormInput.add(BtnDokter6);
        BtnDokter6.setBounds(960, 290, 28, 23);

        label61.setText("Petugas :");
        label61.setName("label61"); // NOI18N
        label61.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label61);
        label61.setBounds(580, 320, 70, 23);

        NipDosis.setEditable(false);
        NipDosis.setName("NipDosis"); // NOI18N
        NipDosis.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(NipDosis);
        NipDosis.setBounds(650, 320, 95, 23);

        NmDosis.setEditable(false);
        NmDosis.setName("NmDosis"); // NOI18N
        NmDosis.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NmDosis);
        NmDosis.setBounds(750, 320, 210, 23);

        BtnDokter7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter7.setMnemonic('2');
        BtnDokter7.setToolTipText("Alt+2");
        BtnDokter7.setName("BtnDokter7"); // NOI18N
        BtnDokter7.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokter7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokter7ActionPerformed(evt);
            }
        });
        BtnDokter7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnDokter7KeyPressed(evt);
            }
        });
        FormInput.add(BtnDokter7);
        BtnDokter7.setBounds(960, 320, 28, 23);

        label62.setText("Petugas :");
        label62.setName("label62"); // NOI18N
        label62.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label62);
        label62.setBounds(580, 350, 70, 23);

        NipEfek.setEditable(false);
        NipEfek.setName("NipEfek"); // NOI18N
        NipEfek.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(NipEfek);
        NipEfek.setBounds(650, 350, 95, 23);

        NmEfek.setEditable(false);
        NmEfek.setName("NmEfek"); // NOI18N
        NmEfek.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NmEfek);
        NmEfek.setBounds(750, 350, 210, 23);

        BtnDokter8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter8.setMnemonic('2');
        BtnDokter8.setToolTipText("Alt+2");
        BtnDokter8.setName("BtnDokter8"); // NOI18N
        BtnDokter8.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokter8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokter8ActionPerformed(evt);
            }
        });
        BtnDokter8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnDokter8KeyPressed(evt);
            }
        });
        FormInput.add(BtnDokter8);
        BtnDokter8.setBounds(960, 350, 28, 23);

        label63.setText("Petugas :");
        label63.setName("label63"); // NOI18N
        label63.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label63);
        label63.setBounds(580, 380, 70, 23);

        NipDiet.setEditable(false);
        NipDiet.setName("NipDiet"); // NOI18N
        NipDiet.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(NipDiet);
        NipDiet.setBounds(650, 380, 95, 23);

        NmDiet.setEditable(false);
        NmDiet.setName("NmDiet"); // NOI18N
        NmDiet.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NmDiet);
        NmDiet.setBounds(750, 380, 210, 23);

        BtnDokter9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter9.setMnemonic('2');
        BtnDokter9.setToolTipText("Alt+2");
        BtnDokter9.setName("BtnDokter9"); // NOI18N
        BtnDokter9.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokter9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokter9ActionPerformed(evt);
            }
        });
        BtnDokter9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnDokter9KeyPressed(evt);
            }
        });
        FormInput.add(BtnDokter9);
        BtnDokter9.setBounds(960, 380, 28, 23);

        label64.setText("Petugas :");
        label64.setName("label64"); // NOI18N
        label64.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label64);
        label64.setBounds(580, 410, 70, 23);

        NipMobilisasi.setEditable(false);
        NipMobilisasi.setName("NipMobilisasi"); // NOI18N
        NipMobilisasi.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(NipMobilisasi);
        NipMobilisasi.setBounds(650, 410, 95, 23);

        NmMobilisasi.setEditable(false);
        NmMobilisasi.setName("NmMobilisasi"); // NOI18N
        NmMobilisasi.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NmMobilisasi);
        NmMobilisasi.setBounds(750, 410, 210, 23);

        BtnDokter10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter10.setMnemonic('2');
        BtnDokter10.setToolTipText("Alt+2");
        BtnDokter10.setName("BtnDokter10"); // NOI18N
        BtnDokter10.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokter10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokter10ActionPerformed(evt);
            }
        });
        BtnDokter10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnDokter10KeyPressed(evt);
            }
        });
        FormInput.add(BtnDokter10);
        BtnDokter10.setBounds(960, 410, 28, 23);

        label65.setText("Petugas :");
        label65.setName("label65"); // NOI18N
        label65.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label65);
        label65.setBounds(580, 440, 70, 23);

        NipPerawatanLuka.setEditable(false);
        NipPerawatanLuka.setName("NipPerawatanLuka"); // NOI18N
        NipPerawatanLuka.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(NipPerawatanLuka);
        NipPerawatanLuka.setBounds(650, 440, 95, 23);

        NmPerawatanLuka.setEditable(false);
        NmPerawatanLuka.setName("NmPerawatanLuka"); // NOI18N
        NmPerawatanLuka.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NmPerawatanLuka);
        NmPerawatanLuka.setBounds(750, 440, 210, 23);

        BtnDokter11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter11.setMnemonic('2');
        BtnDokter11.setToolTipText("Alt+2");
        BtnDokter11.setName("BtnDokter11"); // NOI18N
        BtnDokter11.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokter11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokter11ActionPerformed(evt);
            }
        });
        BtnDokter11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnDokter11KeyPressed(evt);
            }
        });
        FormInput.add(BtnDokter11);
        BtnDokter11.setBounds(960, 440, 28, 23);

        label66.setText("Petugas :");
        label66.setName("label66"); // NOI18N
        label66.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label66);
        label66.setBounds(580, 470, 70, 23);

        NipPenggunaanAlatMedis.setEditable(false);
        NipPenggunaanAlatMedis.setName("NipPenggunaanAlatMedis"); // NOI18N
        NipPenggunaanAlatMedis.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(NipPenggunaanAlatMedis);
        NipPenggunaanAlatMedis.setBounds(650, 470, 95, 23);

        NmPenggunaanAlatMedis.setEditable(false);
        NmPenggunaanAlatMedis.setName("NmPenggunaanAlatMedis"); // NOI18N
        NmPenggunaanAlatMedis.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NmPenggunaanAlatMedis);
        NmPenggunaanAlatMedis.setBounds(750, 470, 210, 23);

        BtnDokter12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter12.setMnemonic('2');
        BtnDokter12.setToolTipText("Alt+2");
        BtnDokter12.setName("BtnDokter12"); // NOI18N
        BtnDokter12.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokter12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokter12ActionPerformed(evt);
            }
        });
        BtnDokter12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnDokter12KeyPressed(evt);
            }
        });
        FormInput.add(BtnDokter12);
        BtnDokter12.setBounds(960, 470, 28, 23);

        label67.setText("Petugas :");
        label67.setName("label67"); // NOI18N
        label67.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label67);
        label67.setBounds(580, 500, 70, 23);

        NipPencegahanKomplikasi.setEditable(false);
        NipPencegahanKomplikasi.setName("NipPencegahanKomplikasi"); // NOI18N
        NipPencegahanKomplikasi.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(NipPencegahanKomplikasi);
        NipPencegahanKomplikasi.setBounds(650, 500, 95, 23);

        NmPencegahanKomplikasi.setEditable(false);
        NmPencegahanKomplikasi.setName("NmPencegahanKomplikasi"); // NOI18N
        NmPencegahanKomplikasi.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NmPencegahanKomplikasi);
        NmPencegahanKomplikasi.setBounds(750, 500, 210, 23);

        BtnDokter13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter13.setMnemonic('2');
        BtnDokter13.setToolTipText("Alt+2");
        BtnDokter13.setName("BtnDokter13"); // NOI18N
        BtnDokter13.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokter13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokter13ActionPerformed(evt);
            }
        });
        BtnDokter13.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnDokter13KeyPressed(evt);
            }
        });
        FormInput.add(BtnDokter13);
        BtnDokter13.setBounds(960, 500, 28, 23);

        label68.setText("Petugas :");
        label68.setName("label68"); // NOI18N
        label68.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label68);
        label68.setBounds(580, 530, 70, 23);

        NipManajemenNyeri.setEditable(false);
        NipManajemenNyeri.setName("NipManajemenNyeri"); // NOI18N
        NipManajemenNyeri.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(NipManajemenNyeri);
        NipManajemenNyeri.setBounds(650, 530, 95, 23);

        NmManajemenNyeri.setEditable(false);
        NmManajemenNyeri.setName("NmManajemenNyeri"); // NOI18N
        NmManajemenNyeri.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NmManajemenNyeri);
        NmManajemenNyeri.setBounds(750, 530, 210, 23);

        BtnDokter14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter14.setMnemonic('2');
        BtnDokter14.setToolTipText("Alt+2");
        BtnDokter14.setName("BtnDokter14"); // NOI18N
        BtnDokter14.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokter14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokter14ActionPerformed(evt);
            }
        });
        BtnDokter14.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnDokter14KeyPressed(evt);
            }
        });
        FormInput.add(BtnDokter14);
        BtnDokter14.setBounds(960, 530, 28, 23);

        label69.setText("Petugas :");
        label69.setName("label69"); // NOI18N
        label69.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label69);
        label69.setBounds(580, 560, 70, 23);

        NipKebersihan.setEditable(false);
        NipKebersihan.setName("NipKebersihan"); // NOI18N
        NipKebersihan.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(NipKebersihan);
        NipKebersihan.setBounds(650, 560, 95, 23);

        NmKebersihan.setEditable(false);
        NmKebersihan.setName("NmKebersihan"); // NOI18N
        NmKebersihan.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NmKebersihan);
        NmKebersihan.setBounds(750, 560, 210, 23);

        BtnDokter15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter15.setMnemonic('2');
        BtnDokter15.setToolTipText("Alt+2");
        BtnDokter15.setName("BtnDokter15"); // NOI18N
        BtnDokter15.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokter15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokter15ActionPerformed(evt);
            }
        });
        BtnDokter15.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnDokter15KeyPressed(evt);
            }
        });
        FormInput.add(BtnDokter15);
        BtnDokter15.setBounds(960, 560, 28, 23);

        label70.setText("Petugas :");
        label70.setName("label70"); // NOI18N
        label70.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label70);
        label70.setBounds(580, 590, 70, 23);

        NipPenundaan.setEditable(false);
        NipPenundaan.setName("NipPenundaan"); // NOI18N
        NipPenundaan.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(NipPenundaan);
        NipPenundaan.setBounds(650, 590, 95, 23);

        NmPenundaan.setEditable(false);
        NmPenundaan.setName("NmPenundaan"); // NOI18N
        NmPenundaan.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NmPenundaan);
        NmPenundaan.setBounds(750, 590, 210, 23);

        BtnDokter16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter16.setMnemonic('2');
        BtnDokter16.setToolTipText("Alt+2");
        BtnDokter16.setName("BtnDokter16"); // NOI18N
        BtnDokter16.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokter16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokter16ActionPerformed(evt);
            }
        });
        BtnDokter16.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnDokter16KeyPressed(evt);
            }
        });
        FormInput.add(BtnDokter16);
        BtnDokter16.setBounds(960, 590, 28, 23);

        label71.setText("Petugas :");
        label71.setName("label71"); // NOI18N
        label71.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label71);
        label71.setBounds(580, 620, 70, 23);

        NipHambatan.setEditable(false);
        NipHambatan.setName("NipHambatan"); // NOI18N
        NipHambatan.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(NipHambatan);
        NipHambatan.setBounds(650, 620, 95, 23);

        NmHambatan.setEditable(false);
        NmHambatan.setName("NmHambatan"); // NOI18N
        NmHambatan.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NmHambatan);
        NmHambatan.setBounds(750, 620, 210, 23);

        BtnDokter17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter17.setMnemonic('2');
        BtnDokter17.setToolTipText("Alt+2");
        BtnDokter17.setName("BtnDokter17"); // NOI18N
        BtnDokter17.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokter17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokter17ActionPerformed(evt);
            }
        });
        BtnDokter17.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnDokter17KeyPressed(evt);
            }
        });
        FormInput.add(BtnDokter17);
        BtnDokter17.setBounds(960, 620, 28, 23);

        label72.setText("Petugas :");
        label72.setName("label72"); // NOI18N
        label72.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label72);
        label72.setBounds(580, 650, 70, 23);

        NipResikoJatuh.setEditable(false);
        NipResikoJatuh.setName("NipResikoJatuh"); // NOI18N
        NipResikoJatuh.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(NipResikoJatuh);
        NipResikoJatuh.setBounds(650, 650, 95, 23);

        NmResikoJatuh.setEditable(false);
        NmResikoJatuh.setName("NmResikoJatuh"); // NOI18N
        NmResikoJatuh.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NmResikoJatuh);
        NmResikoJatuh.setBounds(750, 650, 210, 23);

        BtnDokter18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter18.setMnemonic('2');
        BtnDokter18.setToolTipText("Alt+2");
        BtnDokter18.setName("BtnDokter18"); // NOI18N
        BtnDokter18.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokter18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokter18ActionPerformed(evt);
            }
        });
        BtnDokter18.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnDokter18KeyPressed(evt);
            }
        });
        FormInput.add(BtnDokter18);
        BtnDokter18.setBounds(960, 650, 28, 23);

        label73.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label73.setText("Pencegahan Resiko Jatuh");
        label73.setName("label73"); // NOI18N
        label73.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label73);
        label73.setBounds(40, 650, 170, 23);

        jLabel60.setText("Hasil :");
        jLabel60.setName("jLabel60"); // NOI18N
        FormInput.add(jLabel60);
        jLabel60.setBounds(380, 680, 70, 23);

        Kie_Lain_Lain.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mengerti", "Cukup", "Belum", "-" }));
        Kie_Lain_Lain.setName("Kie_Lain_Lain"); // NOI18N
        Kie_Lain_Lain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Kie_Lain_LainActionPerformed(evt);
            }
        });
        Kie_Lain_Lain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Kie_Lain_LainKeyPressed(evt);
            }
        });
        FormInput.add(Kie_Lain_Lain);
        Kie_Lain_Lain.setBounds(450, 680, 130, 23);

        label74.setText("Petugas :");
        label74.setName("label74"); // NOI18N
        label74.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label74);
        label74.setBounds(580, 680, 70, 23);

        NipLainLain.setEditable(false);
        NipLainLain.setName("NipLainLain"); // NOI18N
        NipLainLain.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(NipLainLain);
        NipLainLain.setBounds(650, 680, 95, 23);

        NmLainLain.setEditable(false);
        NmLainLain.setName("NmLainLain"); // NOI18N
        NmLainLain.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NmLainLain);
        NmLainLain.setBounds(750, 680, 210, 23);

        BtnDokter19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter19.setMnemonic('2');
        BtnDokter19.setToolTipText("Alt+2");
        BtnDokter19.setName("BtnDokter19"); // NOI18N
        BtnDokter19.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokter19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokter19ActionPerformed(evt);
            }
        });
        BtnDokter19.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnDokter19KeyPressed(evt);
            }
        });
        FormInput.add(BtnDokter19);
        BtnDokter19.setBounds(960, 680, 28, 23);

        Jam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        Jam.setName("Jam"); // NOI18N
        Jam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JamKeyPressed(evt);
            }
        });
        FormInput.add(Jam);
        Jam.setBounds(500, 40, 62, 23);

        Menit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        Menit.setName("Menit"); // NOI18N
        Menit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MenitKeyPressed(evt);
            }
        });
        FormInput.add(Menit);
        Menit.setBounds(570, 40, 62, 23);

        Detik.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        Detik.setName("Detik"); // NOI18N
        Detik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DetikKeyPressed(evt);
            }
        });
        FormInput.add(Detik);
        Detik.setBounds(640, 40, 62, 23);

        ChkKejadian.setBorder(null);
        ChkKejadian.setSelected(true);
        ChkKejadian.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkKejadian.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkKejadian.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkKejadian.setName("ChkKejadian"); // NOI18N
        ChkKejadian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkKejadianActionPerformed(evt);
            }
        });
        FormInput.add(ChkKejadian);
        ChkKejadian.setBounds(700, 40, 23, 23);

        jButton1.setText("-");
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        FormInput.add(jButton1);
        jButton1.setBounds(990, 110, 40, 23);

        jButton2.setText("-");
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        FormInput.add(jButton2);
        jButton2.setBounds(990, 140, 40, 23);

        jButton3.setText("-");
        jButton3.setName("jButton3"); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        FormInput.add(jButton3);
        jButton3.setBounds(990, 290, 40, 23);

        jButton4.setText("-");
        jButton4.setName("jButton4"); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        FormInput.add(jButton4);
        jButton4.setBounds(990, 170, 40, 23);

        jButton5.setText("-");
        jButton5.setName("jButton5"); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        FormInput.add(jButton5);
        jButton5.setBounds(990, 200, 40, 23);

        jButton6.setText("-");
        jButton6.setName("jButton6"); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        FormInput.add(jButton6);
        jButton6.setBounds(990, 230, 40, 23);

        jButton7.setText("-");
        jButton7.setName("jButton7"); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        FormInput.add(jButton7);
        jButton7.setBounds(990, 260, 40, 23);

        jButton8.setText("-");
        jButton8.setName("jButton8"); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        FormInput.add(jButton8);
        jButton8.setBounds(990, 470, 40, 23);

        jButton9.setText("-");
        jButton9.setName("jButton9"); // NOI18N
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        FormInput.add(jButton9);
        jButton9.setBounds(990, 380, 40, 23);

        jButton10.setText("-");
        jButton10.setName("jButton10"); // NOI18N
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        FormInput.add(jButton10);
        jButton10.setBounds(990, 350, 40, 23);

        jButton11.setText("-");
        jButton11.setName("jButton11"); // NOI18N
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        FormInput.add(jButton11);
        jButton11.setBounds(990, 410, 40, 23);

        jButton12.setText("-");
        jButton12.setName("jButton12"); // NOI18N
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        FormInput.add(jButton12);
        jButton12.setBounds(990, 440, 40, 23);

        jButton13.setText("-");
        jButton13.setName("jButton13"); // NOI18N
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        FormInput.add(jButton13);
        jButton13.setBounds(990, 320, 40, 23);

        jButton14.setText("-");
        jButton14.setName("jButton14"); // NOI18N
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        FormInput.add(jButton14);
        jButton14.setBounds(990, 680, 40, 23);

        jButton15.setText("-");
        jButton15.setName("jButton15"); // NOI18N
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        FormInput.add(jButton15);
        jButton15.setBounds(990, 560, 40, 23);

        jButton16.setText("-");
        jButton16.setName("jButton16"); // NOI18N
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        FormInput.add(jButton16);
        jButton16.setBounds(990, 530, 40, 23);

        jButton17.setText("-");
        jButton17.setName("jButton17"); // NOI18N
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        FormInput.add(jButton17);
        jButton17.setBounds(990, 590, 40, 23);

        jButton18.setText("-");
        jButton18.setName("jButton18"); // NOI18N
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });
        FormInput.add(jButton18);
        jButton18.setBounds(990, 620, 40, 23);

        jButton19.setText("-");
        jButton19.setName("jButton19"); // NOI18N
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });
        FormInput.add(jButton19);
        jButton19.setBounds(990, 500, 40, 23);

        jButton20.setText("-");
        jButton20.setName("jButton20"); // NOI18N
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });
        FormInput.add(jButton20);
        jButton20.setBounds(990, 650, 40, 23);

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        Lain_Lain.setColumns(20);
        Lain_Lain.setRows(5);
        Lain_Lain.setName("Lain_Lain"); // NOI18N
        jScrollPane1.setViewportView(Lain_Lain);

        FormInput.add(jScrollPane1);
        jScrollPane1.setBounds(190, 680, 220, 60);

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        Lain_Lain1.setColumns(20);
        Lain_Lain1.setRows(5);
        Lain_Lain1.setName("Lain_Lain1"); // NOI18N
        jScrollPane2.setViewportView(Lain_Lain1);

        FormInput.add(jScrollPane2);
        jScrollPane2.setBounds(190, 750, 220, 60);

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        Lain_Lain2.setColumns(20);
        Lain_Lain2.setRows(5);
        Lain_Lain2.setName("Lain_Lain2"); // NOI18N
        jScrollPane3.setViewportView(Lain_Lain2);

        FormInput.add(jScrollPane3);
        jScrollPane3.setBounds(190, 820, 220, 60);

        jScrollPane4.setName("jScrollPane4"); // NOI18N

        Lain_Lain3.setColumns(20);
        Lain_Lain3.setRows(5);
        Lain_Lain3.setName("Lain_Lain3"); // NOI18N
        jScrollPane4.setViewportView(Lain_Lain3);

        FormInput.add(jScrollPane4);
        jScrollPane4.setBounds(190, 890, 220, 60);

        jLabel61.setText("Hasil :");
        jLabel61.setName("jLabel61"); // NOI18N
        FormInput.add(jLabel61);
        jLabel61.setBounds(380, 750, 70, 23);

        Kie_Lain_Lain1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mengerti", "Cukup", "Belum", "-" }));
        Kie_Lain_Lain1.setName("Kie_Lain_Lain1"); // NOI18N
        Kie_Lain_Lain1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Kie_Lain_Lain1ActionPerformed(evt);
            }
        });
        Kie_Lain_Lain1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Kie_Lain_Lain1KeyPressed(evt);
            }
        });
        FormInput.add(Kie_Lain_Lain1);
        Kie_Lain_Lain1.setBounds(450, 750, 130, 23);

        label75.setText("Petugas :");
        label75.setName("label75"); // NOI18N
        label75.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label75);
        label75.setBounds(580, 750, 70, 23);

        NipLainLain1.setEditable(false);
        NipLainLain1.setName("NipLainLain1"); // NOI18N
        NipLainLain1.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(NipLainLain1);
        NipLainLain1.setBounds(650, 750, 95, 23);

        NmLainLain1.setEditable(false);
        NmLainLain1.setName("NmLainLain1"); // NOI18N
        NmLainLain1.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NmLainLain1);
        NmLainLain1.setBounds(750, 750, 210, 23);

        BtnDokter20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter20.setMnemonic('2');
        BtnDokter20.setToolTipText("Alt+2");
        BtnDokter20.setName("BtnDokter20"); // NOI18N
        BtnDokter20.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokter20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokter20ActionPerformed(evt);
            }
        });
        BtnDokter20.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnDokter20KeyPressed(evt);
            }
        });
        FormInput.add(BtnDokter20);
        BtnDokter20.setBounds(960, 750, 28, 23);

        jButton21.setText("-");
        jButton21.setName("jButton21"); // NOI18N
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });
        FormInput.add(jButton21);
        jButton21.setBounds(990, 750, 40, 23);

        jLabel62.setText("Hasil :");
        jLabel62.setName("jLabel62"); // NOI18N
        FormInput.add(jLabel62);
        jLabel62.setBounds(380, 820, 70, 23);

        Kie_Lain_Lain2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mengerti", "Cukup", "Belum", "-" }));
        Kie_Lain_Lain2.setName("Kie_Lain_Lain2"); // NOI18N
        Kie_Lain_Lain2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Kie_Lain_Lain2ActionPerformed(evt);
            }
        });
        Kie_Lain_Lain2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Kie_Lain_Lain2KeyPressed(evt);
            }
        });
        FormInput.add(Kie_Lain_Lain2);
        Kie_Lain_Lain2.setBounds(450, 820, 130, 23);

        label76.setText("Petugas :");
        label76.setName("label76"); // NOI18N
        label76.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label76);
        label76.setBounds(580, 820, 70, 23);

        NipLainLain2.setEditable(false);
        NipLainLain2.setName("NipLainLain2"); // NOI18N
        NipLainLain2.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(NipLainLain2);
        NipLainLain2.setBounds(650, 820, 95, 23);

        NmLainLain2.setEditable(false);
        NmLainLain2.setName("NmLainLain2"); // NOI18N
        NmLainLain2.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NmLainLain2);
        NmLainLain2.setBounds(750, 820, 210, 23);

        BtnDokter21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter21.setMnemonic('2');
        BtnDokter21.setToolTipText("Alt+2");
        BtnDokter21.setName("BtnDokter21"); // NOI18N
        BtnDokter21.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokter21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokter21ActionPerformed(evt);
            }
        });
        BtnDokter21.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnDokter21KeyPressed(evt);
            }
        });
        FormInput.add(BtnDokter21);
        BtnDokter21.setBounds(960, 820, 28, 23);

        jButton22.setText("-");
        jButton22.setName("jButton22"); // NOI18N
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });
        FormInput.add(jButton22);
        jButton22.setBounds(990, 820, 40, 23);

        jLabel63.setText("Hasil :");
        jLabel63.setName("jLabel63"); // NOI18N
        FormInput.add(jLabel63);
        jLabel63.setBounds(380, 890, 70, 23);

        Kie_Lain_Lain3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mengerti", "Cukup", "Belum", "-" }));
        Kie_Lain_Lain3.setName("Kie_Lain_Lain3"); // NOI18N
        Kie_Lain_Lain3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Kie_Lain_Lain3ActionPerformed(evt);
            }
        });
        Kie_Lain_Lain3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Kie_Lain_Lain3KeyPressed(evt);
            }
        });
        FormInput.add(Kie_Lain_Lain3);
        Kie_Lain_Lain3.setBounds(450, 890, 130, 23);

        label77.setText("Petugas :");
        label77.setName("label77"); // NOI18N
        label77.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label77);
        label77.setBounds(580, 890, 70, 23);

        NipLainLain3.setEditable(false);
        NipLainLain3.setName("NipLainLain3"); // NOI18N
        NipLainLain3.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(NipLainLain3);
        NipLainLain3.setBounds(650, 890, 95, 23);

        NmLainLain3.setEditable(false);
        NmLainLain3.setName("NmLainLain3"); // NOI18N
        NmLainLain3.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NmLainLain3);
        NmLainLain3.setBounds(750, 890, 210, 23);

        BtnDokter22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter22.setMnemonic('2');
        BtnDokter22.setToolTipText("Alt+2");
        BtnDokter22.setName("BtnDokter22"); // NOI18N
        BtnDokter22.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokter22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokter22ActionPerformed(evt);
            }
        });
        BtnDokter22.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnDokter22KeyPressed(evt);
            }
        });
        FormInput.add(BtnDokter22);
        BtnDokter22.setBounds(960, 890, 28, 23);

        jButton23.setText("-");
        jButton23.setName("jButton23"); // NOI18N
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });
        FormInput.add(jButton23);
        jButton23.setBounds(990, 890, 40, 23);

        BtnDokter23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter23.setMnemonic('2');
        BtnDokter23.setToolTipText("Alt+2");
        BtnDokter23.setName("BtnDokter23"); // NOI18N
        BtnDokter23.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokter23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokter23ActionPerformed(evt);
            }
        });
        BtnDokter23.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnDokter23KeyPressed(evt);
            }
        });
        FormInput.add(BtnDokter23);
        BtnDokter23.setBounds(150, 680, 28, 23);

        scrollInput.setViewportView(FormInput);

        internalFrame2.add(scrollInput, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Input Layanan", internalFrame2);

        internalFrame3.setBorder(null);
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(452, 200));

        tbObat.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbObat.setComponentPopupMenu(jPopupMenu1);
        tbObat.setName("tbObat"); // NOI18N
        tbObat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbObatMouseClicked(evt);
            }
        });
        tbObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbObatKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbObat);

        internalFrame3.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setText("Tgl.Asuhan :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-06-2026" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCari1);

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass9.add(jLabel21);

        DTPCari2.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-06-2026" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCari2);

        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass9.add(jLabel6);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(195, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('3');
        BtnCari.setToolTipText("Alt+3");
        BtnCari.setName("BtnCari"); // NOI18N
        BtnCari.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariActionPerformed(evt);
            }
        });
        BtnCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCariKeyPressed(evt);
            }
        });
        panelGlass9.add(BtnCari);

        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass9.add(jLabel7);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(LCount);

        internalFrame3.add(panelGlass9, java.awt.BorderLayout.PAGE_END);

        PanelAccor.setBackground(new java.awt.Color(255, 255, 255));
        PanelAccor.setName("PanelAccor"); // NOI18N
        PanelAccor.setPreferredSize(new java.awt.Dimension(430, 43));
        PanelAccor.setLayout(new java.awt.BorderLayout(1, 1));

        ChkAccor.setBackground(new java.awt.Color(255, 250, 250));
        ChkAccor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/kiri.png"))); // NOI18N
        ChkAccor.setSelected(true);
        ChkAccor.setFocusable(false);
        ChkAccor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkAccor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkAccor.setName("ChkAccor"); // NOI18N
        ChkAccor.setPreferredSize(new java.awt.Dimension(15, 20));
        ChkAccor.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/kiri.png"))); // NOI18N
        ChkAccor.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/kanan.png"))); // NOI18N
        ChkAccor.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/kanan.png"))); // NOI18N
        ChkAccor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkAccorActionPerformed(evt);
            }
        });
        PanelAccor.add(ChkAccor, java.awt.BorderLayout.WEST);

        FormPhoto.setBackground(new java.awt.Color(255, 255, 255));
        FormPhoto.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), " Bukti Pelayanan : ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        FormPhoto.setName("FormPhoto"); // NOI18N
        FormPhoto.setPreferredSize(new java.awt.Dimension(115, 73));
        FormPhoto.setLayout(new java.awt.BorderLayout());

        FormPass3.setBackground(new java.awt.Color(255, 255, 255));
        FormPass3.setBorder(null);
        FormPass3.setName("FormPass3"); // NOI18N
        FormPass3.setPreferredSize(new java.awt.Dimension(115, 40));

        btnAmbil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png"))); // NOI18N
        btnAmbil.setMnemonic('U');
        btnAmbil.setText("Ambil");
        btnAmbil.setToolTipText("Alt+U");
        btnAmbil.setName("btnAmbil"); // NOI18N
        btnAmbil.setPreferredSize(new java.awt.Dimension(100, 30));
        btnAmbil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAmbilActionPerformed(evt);
            }
        });
        FormPass3.add(btnAmbil);

        BtnRefreshPhoto1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/refresh.png"))); // NOI18N
        BtnRefreshPhoto1.setMnemonic('U');
        BtnRefreshPhoto1.setText("Refresh");
        BtnRefreshPhoto1.setToolTipText("Alt+U");
        BtnRefreshPhoto1.setName("BtnRefreshPhoto1"); // NOI18N
        BtnRefreshPhoto1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnRefreshPhoto1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRefreshPhoto1ActionPerformed(evt);
            }
        });
        FormPass3.add(BtnRefreshPhoto1);

        FormPhoto.add(FormPass3, java.awt.BorderLayout.PAGE_END);

        Scroll5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        Scroll5.setName("Scroll5"); // NOI18N
        Scroll5.setOpaque(true);
        Scroll5.setPreferredSize(new java.awt.Dimension(200, 200));

        LoadHTML2.setBorder(null);
        LoadHTML2.setName("LoadHTML2"); // NOI18N
        Scroll5.setViewportView(LoadHTML2);

        FormPhoto.add(Scroll5, java.awt.BorderLayout.CENTER);

        PanelAccor.add(FormPhoto, java.awt.BorderLayout.CENTER);

        internalFrame3.add(PanelAccor, java.awt.BorderLayout.EAST);

        TabRawat.addTab("Data Layanan", internalFrame3);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "pasien");
        } else if (NipDiagnosa.getText().trim().equals("") || NmDiagnosa.getText().trim().equals("")) {
            Valid.textKosong(NipDiagnosa, "Petugas");
        } else {
            if (akses.getkode().equals("Admin Utama")) {
                simpan();
            } else {
                if (TanggalRegistrasi.getText().equals("")) {
                    TanggalRegistrasi.setText(Sequel.cariIsi("select concat(reg_periksa.tgl_registrasi,' ',reg_periksa.jam_reg) from reg_periksa where reg_periksa.no_rawat=?", TNoRw.getText()));
                }
                if (Sequel.cekTanggalRegistrasi(TanggalRegistrasi.getText(), Valid.SetTgl(TglEdukasi.getSelectedItem() + "")
                        + " "
                        + Jam.getSelectedItem().toString()
                        + ":"
                        + Menit.getSelectedItem().toString()
                        + ":"
                        + Detik.getSelectedItem().toString()) == true) {
                    simpan();
                }
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed

}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
        emptNip();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
        } else {
            Valid.pindah(evt, BtnSimpan, BtnHapus);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbObat.getSelectedRow() > -1) {
            if (akses.getkode().equals("Admin Utama")) {
                hapus();
            } else {
//                if (NipDiagnosa.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(), 5).toString())) {
//                    if (Sequel.cekTanggal48jam(tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString(), Sequel.ambiltanggalsekarang()) == true) {
//                        hapus();
//                    }
//                } else {
//                    JOptionPane.showMessageDialog(null, "Hanya bisa dihapus oleh psikolog yang bersangkutan..!!");
//                }
                hapus();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih data terlebih dahulu..!!");
        }

}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnHapusActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnBatal, BtnEdit);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if (TNoRM.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Nama Pasien");
        } else if (NmDiagnosa.getText().trim().equals("")) {
            Valid.textKosong(BtnDokter, "Dokter");
        } else {
            if (tbObat.getSelectedRow() > -1) {
                if (akses.getkode().equals("Admin Utama")) {
                    ganti();
                } else {
//                    if (NipDiagnosa.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(), 5).toString())) {
//////                        if(Sequel.cekTanggal48jam(tbObat.getValueAt(tbObat.getSelectedRow(),7).toString(),Sequel.ambiltanggalsekarang())==true){
////                            if(TanggalRegistrasi.getText().equals("")){
////                                TanggalRegistrasi.setText(Seque.lcariIsi("select concat(reg_periksa.tgl_registrasi,' ',reg_periksa.jam_reg) from reg_periksa where reg_periksa.no_rawat=?",TNoRw.getText()));
////                            }
////                            if(Sequel.cekTanggalRegistrasi(TanggalRegistrasi.getText(),Valid.SetTgl(TglAsuhan.getSelectedItem()+"")+" "+TglAsuhan.getSelectedItem().toString().substring(11,19))==true){
//                        ganti();
//                        JOptionPane.showMessageDialog(null, "Berhasil Diupdate");
////                            }
////                        }
//                    } else {
//                        JOptionPane.showMessageDialog(null, "Hanya bisa diganti oleh yang bersangkutan..!!");
//                    }
                    ganti();
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih data terlebih dahulu..!!");
            }
        }
}//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnEditActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnHapus, BtnPrint);
        }
}//GEN-LAST:event_BtnEditKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnKeluarActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnEdit, TCari);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnBatal.requestFocus();
        } else if (tabMode.getRowCount() != 0) {
            try {
                htmlContent = new StringBuilder();
//                htmlContent.append(                             
//                    "<tr class='isi'>"+
//                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>No.Rawat</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>No.RM</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Nama Pasien</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Tgl.Lahir</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>J.K.</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Kode Dokter</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Dokter Yang Menangani</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Tanggal</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Pendamping</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Keterangan Pendamping</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Anamnesa</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Pemeriksaan Fisik & Uji Fungsi</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Diagnosis Medis (ICD - 10)</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Diagnosis Fungsi (ICD - 10)</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Tata Laksana KFR (ICD - 9 CM)</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Anjuran</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Evaluasi</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Suspek Akibat Kerja</b></td>"+
//                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Keterangan Suspek Penyakit Akibat Kerja</b></td>"+
//                    "</tr>"
//                );

                htmlContent.append(
                        "<tr class='isi'>"
                        + "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>No.Rawat</b></td>"
                        + "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>No.RM</b></td>"
                        + "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Nama Pasien</b></td>"
                        + "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Tgl.Lahir</b></td>"
                        + "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>J.K.</b></td>"
                        + "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Kode Dokter</b></td>"
                        + "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Dokter Yang Menangani</b></td>"
                        + "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Tanggal</b></td>"
                        + "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Pendamping</b></td>"
                        + "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Keterangan Pendamping</b></td>"
                        + "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Anamnesa</b></td>"
                        + "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Pemeriksaan Fisik & Uji Fungsi</b></td>"
                        + "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Diagnosis Medis (ICD - 10)</b></td>"
                        + "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Diagnosis Fungsi (ICD - 10)</b></td>"
                        + "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Tata Laksana KFR (ICD - 9 CM)</b></td>"
                        + "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Anjuran</b></td>"
                        + "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Evaluasi</b></td>"
                        + "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Suspek Akibat Kerja</b></td>"
                        + "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Keterangan Suspek Penyakit Akibat Kerja</b></td>"
                        + "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Goal</b></td>"
                        + "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Frekuensi</b></td>"
                        + "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Keterangan Frekuensi</b></td>"
                        + "</tr>"
                );

                for (i = 0; i < tabMode.getRowCount(); i++) {
                    htmlContent.append(
                            "<tr class='isi'>"
                            + "<td valign='top'>" + tbObat.getValueAt(i, 0).toString() + "</td>"
                            + "<td valign='top'>" + tbObat.getValueAt(i, 1).toString() + "</td>"
                            + "<td valign='top'>" + tbObat.getValueAt(i, 2).toString() + "</td>"
                            + "<td valign='top'>" + tbObat.getValueAt(i, 3).toString() + "</td>"
                            + "<td valign='top'>" + tbObat.getValueAt(i, 4).toString() + "</td>"
                            + "<td valign='top'>" + tbObat.getValueAt(i, 5).toString() + "</td>"
                            + "<td valign='top'>" + tbObat.getValueAt(i, 6).toString() + "</td>"
                            + "<td valign='top'>" + tbObat.getValueAt(i, 7).toString() + "</td>"
                            + "<td valign='top'>" + tbObat.getValueAt(i, 8).toString() + "</td>"
                            + "<td valign='top'>" + tbObat.getValueAt(i, 9).toString() + "</td>"
                            + "<td valign='top'>" + tbObat.getValueAt(i, 10).toString() + "</td>"
                            + "<td valign='top'>" + tbObat.getValueAt(i, 11).toString() + "</td>"
                            + "<td valign='top'>" + tbObat.getValueAt(i, 12).toString() + "</td>"
                            + "<td valign='top'>" + tbObat.getValueAt(i, 13).toString() + "</td>"
                            + "<td valign='top'>" + tbObat.getValueAt(i, 14).toString() + "</td>"
                            + "<td valign='top'>" + tbObat.getValueAt(i, 15).toString() + "</td>"
                            + "<td valign='top'>" + tbObat.getValueAt(i, 16).toString() + "</td>"
                            + "<td valign='top'>" + tbObat.getValueAt(i, 17).toString() + "</td>"
                            + "<td valign='top'>" + tbObat.getValueAt(i, 18).toString() + "</td>"
                            + "<td valign='top'>" + tbObat.getValueAt(i, 19).toString() + "</td>"
                            + "<td valign='top'>" + tbObat.getValueAt(i, 20).toString() + "</td>"
                            + "<td valign='top'>" + tbObat.getValueAt(i, 21).toString() + "</td>"
                            + "</tr>");
                }

                LoadHTML.setText(
                        "<html>"
                        + "<table width='2000' border='0' align='center' cellpadding='1px' cellspacing='0' class='tbl_form'>"
                        + htmlContent.toString()
                        + "</table>"
                        + "</html>"
                );

                File g = new File("file2.css");
                BufferedWriter bg = new BufferedWriter(new FileWriter(g));
                bg.write(
                        ".isi td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-bottom: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"
                        + ".isi2 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#323232;}"
                        + ".isi3 td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"
                        + ".isi4 td{font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"
                        + ".isi5 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#AA0000;}"
                        + ".isi6 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#FF0000;}"
                        + ".isi7 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#C8C800;}"
                        + ".isi8 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#00AA00;}"
                        + ".isi9 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#969696;}"
                );
                bg.close();

                File f = new File("DataLayananRawatJalanKedoketranFisikDanRehabilitasi.html");
                BufferedWriter bw = new BufferedWriter(new FileWriter(f));
                bw.write(LoadHTML.getText().replaceAll("<head>", "<head>"
                        + "<link href=\"file2.css\" rel=\"stylesheet\" type=\"text/css\" />"
                        + "<table width='2000px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                        + "<tr class='isi2'>"
                        + "<td valign='top' align='center'>"
                        + "<font size='4' face='Tahoma'>" + akses.getnamars() + "</font><br>"
                        + akses.getalamatrs() + ", " + akses.getkabupatenrs() + ", " + akses.getpropinsirs() + "<br>"
                        + akses.getkontakrs() + ", E-mail : " + akses.getemailrs() + "<br><br>"
                        + "<font size='2' face='Tahoma'>DATA LAYANAN RAWAT JALAN KEDOKTERAN FISIK & REHABILITASI<br><br></font>"
                        + "</td>"
                        + "</tr>"
                        + "</table>")
                );
                bw.close();
                Desktop.getDesktop().browse(f.toURI());
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            }
        }
        this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnEdit, BtnKeluar);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar.requestFocus();
        }
}//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        runBackground(() -> tampil());
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        runBackground(() -> tampil());
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            TCari.setText("");
            runBackground(() -> tampil());
        } else {
            Valid.pindah(evt, BtnCari, TPasien);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObatMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
            try {
                isPhoto();
                panggilPhoto();
            } catch (java.lang.NullPointerException e) {
            }
            if ((evt.getClickCount() == 2) && (tbObat.getSelectedColumn() == 0)) {
                TabRawat.setSelectedIndex(0);
            }
        }
}//GEN-LAST:event_tbObatMouseClicked

    private void tbObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObatKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            } else if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
                try {
                    getData();
                    TabRawat.setSelectedIndex(0);
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbObatKeyPressed

    private void MnCetakKieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakKieActionPerformed
        if (tbObat.getSelectedRow() > -1) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select setting.logo from setting"));
            finger = Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?", tbObat.getValueAt(tbObat.getSelectedRow(), 5).toString());
            param.put("finger", "Dikeluarkan di " + akses.getnamars() + ", Kabupaten/Kota " + akses.getkabupatenrs() + "\nDitandatangani secara elektronik oleh " + tbObat.getValueAt(tbObat.getSelectedRow(), 6).toString() + "\nID " + (finger.equals("") ? tbObat.getValueAt(tbObat.getSelectedRow(), 5).toString() : finger) + "\n" + Valid.SetTgl3(tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString()));
            Valid.MyReportqry("rptCetakLayananKedokteranFisikRehabilitasi.jasper", "report", "::[ Lembar Formulir Layanan Rawat Jalan Kedokteran Fisik & Rehabilitasi ]::",
                    "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,layanan_kedokteran_fisik_rehabilitasi.tanggal,"
                    + "layanan_kedokteran_fisik_rehabilitasi.kd_dokter,dokter.nm_dokter,layanan_kedokteran_fisik_rehabilitasi.pendamping,layanan_kedokteran_fisik_rehabilitasi.keterangan_pendamping,"
                    + "layanan_kedokteran_fisik_rehabilitasi.anamnesa,layanan_kedokteran_fisik_rehabilitasi.pemeriksaan_fisik,layanan_kedokteran_fisik_rehabilitasi.diagnosa_medis,concat('http://" + koneksiDB.HOSTHYBRIDWEB() + ":" + koneksiDB.PORTWEB() + "/" + koneksiDB.HYBRIDWEB() + "/layanankedokteranfisikrehabilitasi/',bukti_layanan_kedokteran_fisik_rehabilitasi.photo) as photo,"
                    + "layanan_kedokteran_fisik_rehabilitasi.diagnosa_fungsi,layanan_kedokteran_fisik_rehabilitasi.tatalaksana,layanan_kedokteran_fisik_rehabilitasi.anjuran,layanan_kedokteran_fisik_rehabilitasi.evaluasi,"
                    + "layanan_kedokteran_fisik_rehabilitasi.suspek_penyakit_kerja,layanan_kedokteran_fisik_rehabilitasi.keterangan_suspek_penyakit_kerja,kelurahan.nm_kel,kecamatan.nm_kec,kabupaten.nm_kab,"
                    + "propinsi.nm_prop,poliklinik.nm_poli,pasien.alamat from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis inner join kelurahan on kelurahan.kd_kel=pasien.kd_kel "
                    + "inner join kecamatan on kecamatan.kd_kec=pasien.kd_kec inner join kabupaten on kabupaten.kd_kab=pasien.kd_kab inner join propinsi on propinsi.kd_prop=pasien.kd_prop "
                    + "inner join poliklinik on reg_periksa.kd_poli=poliklinik.kd_poli inner join layanan_kedokteran_fisik_rehabilitasi on reg_periksa.no_rawat=layanan_kedokteran_fisik_rehabilitasi.no_rawat "
                    + "inner join dokter on layanan_kedokteran_fisik_rehabilitasi.kd_dokter=dokter.kd_dokter inner join bukti_layanan_kedokteran_fisik_rehabilitasi on layanan_kedokteran_fisik_rehabilitasi.no_rawat=bukti_layanan_kedokteran_fisik_rehabilitasi.no_rawat "
                    + "where layanan_kedokteran_fisik_rehabilitasi.no_rawat='" + tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString() + "'", param);
        }
    }//GEN-LAST:event_MnCetakKieActionPerformed

    private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            isRawat();
        } else {
            //Valid.pindah(evt,TCari,BtnPetugas);
        }
    }//GEN-LAST:event_TNoRwKeyPressed

    private void TglEdukasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglEdukasiKeyPressed
        //Valid.pindah(evt,BtnPetugas,DiagnosaPraTindakan);
    }//GEN-LAST:event_TglEdukasiKeyPressed

    private void BtnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterActionPerformed
        if (pegawai == null || !pegawai.isDisplayable()) {
            pegawai = new DlgCariPegawai(null, false);
            pegawai.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            pegawai.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    if (pegawai.getTable().getSelectedRow() != -1) {
                        NipDiagnosa.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 0).toString());
                        NmDiagnosa.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 1).toString());
                    }
                    BtnDokter.requestFocus();
                    pegawai = null;
                }
            });
            pegawai.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
            pegawai.setLocationRelativeTo(internalFrame1);
        }
        if (pegawai == null) {
            return;
        }
//        petugas.isCek();
        if (pegawai.isVisible()) {
            pegawai.toFront();
            return;
        }
        pegawai.setVisible(true);
    }//GEN-LAST:event_BtnDokterActionPerformed

    private void BtnDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokterKeyPressed
        //Valid.pindah(evt,BtnPetugas,TglAsuhan);
    }//GEN-LAST:event_BtnDokterKeyPressed

    private void ChkAccorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkAccorActionPerformed
        if (tbObat.getSelectedRow() != -1) {
            isPhoto();
            panggilPhoto();
        } else {
            ChkAccor.setSelected(false);
            JOptionPane.showMessageDialog(null, "Silahkan pilih No.Pernyataan..!!!");
        }
    }//GEN-LAST:event_ChkAccorActionPerformed

    private void btnAmbilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAmbilActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
            TCari.requestFocus();
        } else {
            if (tbObat.getSelectedRow() > -1) {
                Sequel.queryu("delete from antripelaksanaankie");
                Sequel.queryu("insert into antripelaksanaankie values('" + tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString() + "','" + tbObat.getValueAt(tbObat.getSelectedRow(), 5).toString() + "')");
                Sequel.queryu(
                        "delete from bukti_pelaksanaan_kie where no_rawat='"
                        + tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString()
                        + "' and tanggal='"
                        + tbObat.getValueAt(tbObat.getSelectedRow(), 5).toString()
                        + "'"
                );
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih data terlebih dahulu..!!");
            }
        }
    }//GEN-LAST:event_btnAmbilActionPerformed

    private void BtnRefreshPhoto1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRefreshPhoto1ActionPerformed
        if (tbObat.getSelectedRow() > -1) {
            panggilPhoto();
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih data pelayanan terlebih dahulu..!!");
        }
    }//GEN-LAST:event_BtnRefreshPhoto1ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        if (koneksiDB.CARICEPAT().equals("aktif")) {
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if (TCari.getText().length() > 2) {
                        runBackground(() -> tampil());
                    }
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    if (TCari.getText().length() > 2) {
                        runBackground(() -> tampil());
                    }
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    if (TCari.getText().length() > 2) {
                        runBackground(() -> tampil());
                    }
                }
            });
        }
    }//GEN-LAST:event_formWindowOpened

    private void Kie_Hambatan_PelayananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Kie_Hambatan_PelayananKeyPressed
        Valid.pindah(evt, Hambatan_Pelayanan, BtnSimpan);
    }//GEN-LAST:event_Kie_Hambatan_PelayananKeyPressed

    private void Hambatan_PelayananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Hambatan_PelayananKeyPressed
//        Valid.pindah(evt, KeteranganKepada, Kie_Hambatan_Pelayanan);
    }//GEN-LAST:event_Hambatan_PelayananKeyPressed

    private void Kie_Hambatan_PelayananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Kie_Hambatan_PelayananActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Kie_Hambatan_PelayananActionPerformed

    private void Tanda_Gejala_PenyakitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tanda_Gejala_PenyakitKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Tanda_Gejala_PenyakitKeyPressed

    private void Kie_Tanda_Gejala_PenyakitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Kie_Tanda_Gejala_PenyakitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Kie_Tanda_Gejala_PenyakitActionPerformed

    private void Kie_Tanda_Gejala_PenyakitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Kie_Tanda_Gejala_PenyakitKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Kie_Tanda_Gejala_PenyakitKeyPressed

    private void Ren_Tindakan_MedisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Ren_Tindakan_MedisKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Ren_Tindakan_MedisKeyPressed

    private void Kie_Ren_Tindakan_MedisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Kie_Ren_Tindakan_MedisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Kie_Ren_Tindakan_MedisActionPerformed

    private void Kie_Ren_Tindakan_MedisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Kie_Ren_Tindakan_MedisKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Kie_Ren_Tindakan_MedisKeyPressed

    private void Pengobatan_DiberikanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Pengobatan_DiberikanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Pengobatan_DiberikanKeyPressed

    private void Kie_Pengobatan_DiberikanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Kie_Pengobatan_DiberikanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Kie_Pengobatan_DiberikanActionPerformed

    private void Kie_Pengobatan_DiberikanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Kie_Pengobatan_DiberikanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Kie_Pengobatan_DiberikanKeyPressed

    private void Proses_PerawatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Proses_PerawatanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Proses_PerawatanKeyPressed

    private void Kie_Proses_PerawatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Kie_Proses_PerawatanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Kie_Proses_PerawatanActionPerformed

    private void Kie_Proses_PerawatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Kie_Proses_PerawatanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Kie_Proses_PerawatanKeyPressed

    private void Alternatif_PengobatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Alternatif_PengobatanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Alternatif_PengobatanKeyPressed

    private void Kie_Alternatif_PengobatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Kie_Alternatif_PengobatanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Kie_Alternatif_PengobatanActionPerformed

    private void Kie_Alternatif_PengobatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Kie_Alternatif_PengobatanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Kie_Alternatif_PengobatanKeyPressed

    private void Cara_Penggunaan_ObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Cara_Penggunaan_ObatKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Cara_Penggunaan_ObatKeyPressed

    private void Kie_Cara_Penggunaan_ObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Kie_Cara_Penggunaan_ObatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Kie_Cara_Penggunaan_ObatActionPerformed

    private void Kie_Cara_Penggunaan_ObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Kie_Cara_Penggunaan_ObatKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Kie_Cara_Penggunaan_ObatKeyPressed

    private void Dosis_Jadwal_ObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Dosis_Jadwal_ObatKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Dosis_Jadwal_ObatKeyPressed

    private void Kie_Dosis_Jadwal_ObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Kie_Dosis_Jadwal_ObatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Kie_Dosis_Jadwal_ObatActionPerformed

    private void Kie_Dosis_Jadwal_ObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Kie_Dosis_Jadwal_ObatKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Kie_Dosis_Jadwal_ObatKeyPressed

    private void Efek_Samping_ObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Efek_Samping_ObatKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Efek_Samping_ObatKeyPressed

    private void Kie_Efek_Samping_ObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Kie_Efek_Samping_ObatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Kie_Efek_Samping_ObatActionPerformed

    private void Kie_Efek_Samping_ObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Kie_Efek_Samping_ObatKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Kie_Efek_Samping_ObatKeyPressed

    private void Diet_NutrisiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Diet_NutrisiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Diet_NutrisiKeyPressed

    private void Kie_Diet_NutrisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Kie_Diet_NutrisiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Kie_Diet_NutrisiActionPerformed

    private void Kie_Diet_NutrisiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Kie_Diet_NutrisiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Kie_Diet_NutrisiKeyPressed

    private void Aktivitas_MobilisasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Aktivitas_MobilisasiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Aktivitas_MobilisasiKeyPressed

    private void Kie_Aktivitas_MobilisasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Kie_Aktivitas_MobilisasiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Kie_Aktivitas_MobilisasiActionPerformed

    private void Kie_Aktivitas_MobilisasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Kie_Aktivitas_MobilisasiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Kie_Aktivitas_MobilisasiKeyPressed

    private void Perawatan_LukaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Perawatan_LukaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Perawatan_LukaKeyPressed

    private void Kie_Perawatan_LukaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Kie_Perawatan_LukaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Kie_Perawatan_LukaActionPerformed

    private void Kie_Perawatan_LukaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Kie_Perawatan_LukaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Kie_Perawatan_LukaKeyPressed

    private void Penggunaan_Alat_MedisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Penggunaan_Alat_MedisKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Penggunaan_Alat_MedisKeyPressed

    private void Kie_Penggunaan_Alat_MedisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Kie_Penggunaan_Alat_MedisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Kie_Penggunaan_Alat_MedisActionPerformed

    private void Kie_Penggunaan_Alat_MedisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Kie_Penggunaan_Alat_MedisKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Kie_Penggunaan_Alat_MedisKeyPressed

    private void Pencegahan_KomplikasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Pencegahan_KomplikasiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Pencegahan_KomplikasiKeyPressed

    private void Kie_Pencegahan_KomplikasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Kie_Pencegahan_KomplikasiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Kie_Pencegahan_KomplikasiActionPerformed

    private void Kie_Pencegahan_KomplikasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Kie_Pencegahan_KomplikasiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Kie_Pencegahan_KomplikasiKeyPressed

    private void Pencegahan_Resiko_JatuhKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Pencegahan_Resiko_JatuhKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Pencegahan_Resiko_JatuhKeyPressed

    private void Kie_Pencegahan_Resiko_JatuhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Kie_Pencegahan_Resiko_JatuhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Kie_Pencegahan_Resiko_JatuhActionPerformed

    private void Kie_Pencegahan_Resiko_JatuhKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Kie_Pencegahan_Resiko_JatuhKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Kie_Pencegahan_Resiko_JatuhKeyPressed

    private void Manajemen_NyeriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Manajemen_NyeriKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Manajemen_NyeriKeyPressed

    private void Kie_Manajemen_NyeriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Kie_Manajemen_NyeriActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Kie_Manajemen_NyeriActionPerformed

    private void Kie_Manajemen_NyeriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Kie_Manajemen_NyeriKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Kie_Manajemen_NyeriKeyPressed

    private void Diagnosis_PenyakitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Diagnosis_PenyakitKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Diagnosis_PenyakitKeyPressed

    private void Kie_Diagnosis_PenyakitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Kie_Diagnosis_PenyakitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Kie_Diagnosis_PenyakitActionPerformed

    private void Kie_Diagnosis_PenyakitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Kie_Diagnosis_PenyakitKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Kie_Diagnosis_PenyakitKeyPressed

    private void Kebersihan_DiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Kebersihan_DiriKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Kebersihan_DiriKeyPressed

    private void Kie_Kebersihan_DiriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Kie_Kebersihan_DiriActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Kie_Kebersihan_DiriActionPerformed

    private void Kie_Kebersihan_DiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Kie_Kebersihan_DiriKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Kie_Kebersihan_DiriKeyPressed

    private void Penundaan_PelayananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Penundaan_PelayananKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Penundaan_PelayananKeyPressed

    private void Kie_Penundaan_PelayananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Kie_Penundaan_PelayananActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Kie_Penundaan_PelayananActionPerformed

    private void Kie_Penundaan_PelayananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Kie_Penundaan_PelayananKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Kie_Penundaan_PelayananKeyPressed

    private void BtnDokter1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokter1ActionPerformed
        if (pegawai == null || !pegawai.isDisplayable()) {
            pegawai = new DlgCariPegawai(null, false);
            pegawai.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            pegawai.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    if (pegawai.getTable().getSelectedRow() != -1) {
                        NipTanda.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 0).toString());
                        NmTanda.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 1).toString());
                    }
                    BtnDokter.requestFocus();
                    pegawai = null;
                }
            });
            pegawai.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
            pegawai.setLocationRelativeTo(internalFrame1);
        }
        if (pegawai == null) {
            return;
        }
//        petugas.isCek();
        if (pegawai.isVisible()) {
            pegawai.toFront();
            return;
        }
        pegawai.setVisible(true);
    }//GEN-LAST:event_BtnDokter1ActionPerformed

    private void BtnDokter1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokter1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnDokter1KeyPressed

    private void BtnDokter2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokter2ActionPerformed
        if (pegawai == null || !pegawai.isDisplayable()) {
            pegawai = new DlgCariPegawai(null, false);
            pegawai.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            pegawai.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    if (pegawai.getTable().getSelectedRow() != -1) {
                        NipRenMedis.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 0).toString());
                        NmRenMedis.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 1).toString());
                    }
                    BtnDokter2.requestFocus();
                    pegawai = null;
                }
            });
            pegawai.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
            pegawai.setLocationRelativeTo(internalFrame1);
        }
        if (pegawai == null) {
            return;
        }
//        petugas.isCek();
        if (pegawai.isVisible()) {
            pegawai.toFront();
            return;
        }
        pegawai.setVisible(true);
    }//GEN-LAST:event_BtnDokter2ActionPerformed

    private void BtnDokter2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokter2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnDokter2KeyPressed

    private void BtnDokter3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokter3ActionPerformed
        if (pegawai == null || !pegawai.isDisplayable()) {
            pegawai = new DlgCariPegawai(null, false);
            pegawai.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            pegawai.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    if (pegawai.getTable().getSelectedRow() != -1) {
                        NipPengobatan.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 0).toString());
                        NmPengobatan.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 1).toString());
                    }
                    BtnDokter3.requestFocus();
                    pegawai = null;
                }
            });
            pegawai.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
            pegawai.setLocationRelativeTo(internalFrame1);
        }
        if (pegawai == null) {
            return;
        }
//        petugas.isCek();
        if (pegawai.isVisible()) {
            pegawai.toFront();
            return;
        }
        pegawai.setVisible(true);
    }//GEN-LAST:event_BtnDokter3ActionPerformed

    private void BtnDokter3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokter3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnDokter3KeyPressed

    private void BtnDokter4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokter4ActionPerformed
        if (pegawai == null || !pegawai.isDisplayable()) {
            pegawai = new DlgCariPegawai(null, false);
            pegawai.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            pegawai.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    if (pegawai.getTable().getSelectedRow() != -1) {
                        NipProsesRawat.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 0).toString());
                        NmProsesRawat.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 1).toString());
                    }
                    BtnDokter4.requestFocus();
                    pegawai = null;
                }
            });
            pegawai.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
            pegawai.setLocationRelativeTo(internalFrame1);
        }
        if (pegawai == null) {
            return;
        }
//        petugas.isCek();
        if (pegawai.isVisible()) {
            pegawai.toFront();
            return;
        }
        pegawai.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_BtnDokter4ActionPerformed

    private void BtnDokter4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokter4KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnDokter4KeyPressed

    private void BtnDokter5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokter5ActionPerformed
        if (pegawai == null || !pegawai.isDisplayable()) {
            pegawai = new DlgCariPegawai(null, false);
            pegawai.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            pegawai.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    if (pegawai.getTable().getSelectedRow() != -1) {
                        NipAltPengobatan.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 0).toString());
                        NmAltPengobatan.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 1).toString());
                    }
                    BtnDokter5.requestFocus();
                    pegawai = null;
                }
            });
            pegawai.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
            pegawai.setLocationRelativeTo(internalFrame1);
        }
        if (pegawai == null) {
            return;
        }
//        petugas.isCek();
        if (pegawai.isVisible()) {
            pegawai.toFront();
            return;
        }
        pegawai.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_BtnDokter5ActionPerformed

    private void BtnDokter5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokter5KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnDokter5KeyPressed

    private void BtnDokter6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokter6ActionPerformed
        if (pegawai == null || !pegawai.isDisplayable()) {
            pegawai = new DlgCariPegawai(null, false);
            pegawai.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            pegawai.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    if (pegawai.getTable().getSelectedRow() != -1) {
                        NipCaraPenggunaan.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 0).toString());
                        NmCaraPenggunaan.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 1).toString());
                    }
                    BtnDokter6.requestFocus();
                    pegawai = null;
                }
            });
            pegawai.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
            pegawai.setLocationRelativeTo(internalFrame1);
        }
        if (pegawai == null) {
            return;
        }
//        petugas.isCek();
        if (pegawai.isVisible()) {
            pegawai.toFront();
            return;
        }
        pegawai.setVisible(true);
    }//GEN-LAST:event_BtnDokter6ActionPerformed

    private void BtnDokter6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokter6KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnDokter6KeyPressed

    private void BtnDokter7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokter7ActionPerformed
        if (pegawai == null || !pegawai.isDisplayable()) {
            pegawai = new DlgCariPegawai(null, false);
            pegawai.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            pegawai.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    if (pegawai.getTable().getSelectedRow() != -1) {
                        NipDosis.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 0).toString());
                        NmDosis.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 1).toString());
                    }
                    BtnDokter7.requestFocus();
                    pegawai = null;
                }
            });
            pegawai.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
            pegawai.setLocationRelativeTo(internalFrame1);
        }
        if (pegawai == null) {
            return;
        }
//        petugas.isCek();
        if (pegawai.isVisible()) {
            pegawai.toFront();
            return;
        }
        pegawai.setVisible(true);
    }//GEN-LAST:event_BtnDokter7ActionPerformed

    private void BtnDokter7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokter7KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnDokter7KeyPressed

    private void BtnDokter8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokter8ActionPerformed
        if (pegawai == null || !pegawai.isDisplayable()) {
            pegawai = new DlgCariPegawai(null, false);
            pegawai.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            pegawai.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    if (pegawai.getTable().getSelectedRow() != -1) {
                        NipEfek.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 0).toString());
                        NmEfek.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 1).toString());
                    }
                    BtnDokter8.requestFocus();
                    pegawai = null;
                }
            });
            pegawai.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
            pegawai.setLocationRelativeTo(internalFrame1);
        }
        if (pegawai == null) {
            return;
        }
//        petugas.isCek();
        if (pegawai.isVisible()) {
            pegawai.toFront();
            return;
        }
        pegawai.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_BtnDokter8ActionPerformed

    private void BtnDokter8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokter8KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnDokter8KeyPressed

    private void BtnDokter9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokter9ActionPerformed
        if (pegawai == null || !pegawai.isDisplayable()) {
            pegawai = new DlgCariPegawai(null, false);
            pegawai.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            pegawai.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    if (pegawai.getTable().getSelectedRow() != -1) {
                        NipDiet.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 0).toString());
                        NmDiet.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 1).toString());
                    }
                    BtnDokter9.requestFocus();
                    pegawai = null;
                }
            });
            pegawai.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
            pegawai.setLocationRelativeTo(internalFrame1);
        }
        if (pegawai == null) {
            return;
        }
//        petugas.isCek();
        if (pegawai.isVisible()) {
            pegawai.toFront();
            return;
        }
        pegawai.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_BtnDokter9ActionPerformed

    private void BtnDokter9KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokter9KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnDokter9KeyPressed

    private void BtnDokter10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokter10ActionPerformed
        if (pegawai == null || !pegawai.isDisplayable()) {
            pegawai = new DlgCariPegawai(null, false);
            pegawai.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            pegawai.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    if (pegawai.getTable().getSelectedRow() != -1) {
                        NipMobilisasi.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 0).toString());
                        NmMobilisasi.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 1).toString());
                    }
                    BtnDokter10.requestFocus();
                    pegawai = null;
                }
            });
            pegawai.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
            pegawai.setLocationRelativeTo(internalFrame1);
        }
        if (pegawai == null) {
            return;
        }
//        petugas.isCek();
        if (pegawai.isVisible()) {
            pegawai.toFront();
            return;
        }
        pegawai.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_BtnDokter10ActionPerformed

    private void BtnDokter10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokter10KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnDokter10KeyPressed

    private void BtnDokter11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokter11ActionPerformed
        if (pegawai == null || !pegawai.isDisplayable()) {
            pegawai = new DlgCariPegawai(null, false);
            pegawai.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            pegawai.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    if (pegawai.getTable().getSelectedRow() != -1) {
                        NipPerawatanLuka.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 0).toString());
                        NmPerawatanLuka.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 1).toString());
                    }
                    BtnDokter11.requestFocus();
                    pegawai = null;
                }
            });
            pegawai.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
            pegawai.setLocationRelativeTo(internalFrame1);
        }
        if (pegawai == null) {
            return;
        }
//        petugas.isCek();
        if (pegawai.isVisible()) {
            pegawai.toFront();
            return;
        }
        pegawai.setVisible(true);
    }//GEN-LAST:event_BtnDokter11ActionPerformed

    private void BtnDokter11KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokter11KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnDokter11KeyPressed

    private void BtnDokter12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokter12ActionPerformed
        if (pegawai == null || !pegawai.isDisplayable()) {
            pegawai = new DlgCariPegawai(null, false);
            pegawai.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            pegawai.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    if (pegawai.getTable().getSelectedRow() != -1) {
                        NipPenggunaanAlatMedis.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 0).toString());
                        NmPenggunaanAlatMedis.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 1).toString());
                    }
                    BtnDokter12.requestFocus();
                    pegawai = null;
                }
            });
            pegawai.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
            pegawai.setLocationRelativeTo(internalFrame1);
        }
        if (pegawai == null) {
            return;
        }
//        petugas.isCek();
        if (pegawai.isVisible()) {
            pegawai.toFront();
            return;
        }
        pegawai.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_BtnDokter12ActionPerformed

    private void BtnDokter12KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokter12KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnDokter12KeyPressed

    private void BtnDokter13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokter13ActionPerformed
        if (pegawai == null || !pegawai.isDisplayable()) {
            pegawai = new DlgCariPegawai(null, false);
            pegawai.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            pegawai.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    if (pegawai.getTable().getSelectedRow() != -1) {
                        NipPencegahanKomplikasi.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 0).toString());
                        NmPencegahanKomplikasi.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 1).toString());
                    }
                    BtnDokter13.requestFocus();
                    pegawai = null;
                }
            });
            pegawai.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
            pegawai.setLocationRelativeTo(internalFrame1);
        }
        if (pegawai == null) {
            return;
        }
//        petugas.isCek();
        if (pegawai.isVisible()) {
            pegawai.toFront();
            return;
        }
        pegawai.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_BtnDokter13ActionPerformed

    private void BtnDokter13KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokter13KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnDokter13KeyPressed

    private void BtnDokter14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokter14ActionPerformed
        if (pegawai == null || !pegawai.isDisplayable()) {
            pegawai = new DlgCariPegawai(null, false);
            pegawai.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            pegawai.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    if (pegawai.getTable().getSelectedRow() != -1) {
                        NipManajemenNyeri.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 0).toString());
                        NmManajemenNyeri.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 1).toString());
                    }
                    BtnDokter14.requestFocus();
                    pegawai = null;
                }
            });
            pegawai.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
            pegawai.setLocationRelativeTo(internalFrame1);
        }
        if (pegawai == null) {
            return;
        }
//        petugas.isCek();
        if (pegawai.isVisible()) {
            pegawai.toFront();
            return;
        }
        pegawai.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_BtnDokter14ActionPerformed

    private void BtnDokter14KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokter14KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnDokter14KeyPressed

    private void BtnDokter15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokter15ActionPerformed
        if (pegawai == null || !pegawai.isDisplayable()) {
            pegawai = new DlgCariPegawai(null, false);
            pegawai.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            pegawai.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    if (pegawai.getTable().getSelectedRow() != -1) {
                        NipKebersihan.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 0).toString());
                        NmKebersihan.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 1).toString());
                    }
                    BtnDokter15.requestFocus();
                    pegawai = null;
                }
            });
            pegawai.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
            pegawai.setLocationRelativeTo(internalFrame1);
        }
        if (pegawai == null) {
            return;
        }
//        petugas.isCek();
        if (pegawai.isVisible()) {
            pegawai.toFront();
            return;
        }
        pegawai.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_BtnDokter15ActionPerformed

    private void BtnDokter15KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokter15KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnDokter15KeyPressed

    private void BtnDokter16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokter16ActionPerformed
        if (pegawai == null || !pegawai.isDisplayable()) {
            pegawai = new DlgCariPegawai(null, false);
            pegawai.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            pegawai.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    if (pegawai.getTable().getSelectedRow() != -1) {
                        NipPenundaan.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 0).toString());
                        NmPenundaan.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 1).toString());
                    }
                    BtnDokter16.requestFocus();
                    pegawai = null;
                }
            });
            pegawai.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
            pegawai.setLocationRelativeTo(internalFrame1);
        }
        if (pegawai == null) {
            return;
        }
//        petugas.isCek();
        if (pegawai.isVisible()) {
            pegawai.toFront();
            return;
        }
        pegawai.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_BtnDokter16ActionPerformed

    private void BtnDokter16KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokter16KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnDokter16KeyPressed

    private void BtnDokter17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokter17ActionPerformed
        if (pegawai == null || !pegawai.isDisplayable()) {
            pegawai = new DlgCariPegawai(null, false);
            pegawai.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            pegawai.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    if (pegawai.getTable().getSelectedRow() != -1) {
                        NipHambatan.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 0).toString());
                        NmHambatan.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 1).toString());
                    }
                    BtnDokter17.requestFocus();
                    pegawai = null;
                }
            });
            pegawai.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
            pegawai.setLocationRelativeTo(internalFrame1);
        }
        if (pegawai == null) {
            return;
        }
//        petugas.isCek();
        if (pegawai.isVisible()) {
            pegawai.toFront();
            return;
        }
        pegawai.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_BtnDokter17ActionPerformed

    private void BtnDokter17KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokter17KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnDokter17KeyPressed

    private void BtnDokter18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokter18ActionPerformed
        if (pegawai == null || !pegawai.isDisplayable()) {
            pegawai = new DlgCariPegawai(null, false);
            pegawai.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            pegawai.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    if (pegawai.getTable().getSelectedRow() != -1) {
                        NipResikoJatuh.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 0).toString());
                        NmResikoJatuh.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 1).toString());
                    }
                    BtnDokter18.requestFocus();
                    pegawai = null;
                }
            });
            pegawai.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
            pegawai.setLocationRelativeTo(internalFrame1);
        }
        if (pegawai == null) {
            return;
        }
//        petugas.isCek();
        if (pegawai.isVisible()) {
            pegawai.toFront();
            return;
        }
        pegawai.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_BtnDokter18ActionPerformed

    private void BtnDokter18KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokter18KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnDokter18KeyPressed

    private void JkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JkActionPerformed

    private void Kie_Lain_LainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Kie_Lain_LainActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Kie_Lain_LainActionPerformed

    private void Kie_Lain_LainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Kie_Lain_LainKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Kie_Lain_LainKeyPressed

    private void BtnDokter19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokter19ActionPerformed
        if (pegawai == null || !pegawai.isDisplayable()) {
            pegawai = new DlgCariPegawai(null, false);
            pegawai.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            pegawai.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    if (pegawai.getTable().getSelectedRow() != -1) {
                        NipLainLain.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 0).toString());
                        NmLainLain.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 1).toString());
                    }
                    BtnDokter19.requestFocus();
                    pegawai = null;
                }
            });
            pegawai.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
            pegawai.setLocationRelativeTo(internalFrame1);
        }
        if (pegawai == null) {
            return;
        }
//        petugas.isCek();
        if (pegawai.isVisible()) {
            pegawai.toFront();
            return;
        }
        pegawai.setVisible(true);        // TODO add your handling code here:        // TODO add your handling code here:
    }//GEN-LAST:event_BtnDokter19ActionPerformed

    private void BtnDokter19KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokter19KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnDokter19KeyPressed

    private void JamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JamKeyPressed
        Valid.pindah(evt, TglEdukasi, Menit);
    }//GEN-LAST:event_JamKeyPressed

    private void MenitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MenitKeyPressed
        Valid.pindah(evt, Jam, Detik);
    }//GEN-LAST:event_MenitKeyPressed

    private void DetikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DetikKeyPressed

    }//GEN-LAST:event_DetikKeyPressed

    private void ChkKejadianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkKejadianActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkKejadianActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Diagnosis_Penyakit.setSelectedItem("-");
        Kie_Diagnosis_Penyakit.setSelectedItem("-");
        NipDiagnosa.setText("-");
        NmDiagnosa.setText("-");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Tanda_Gejala_Penyakit.setSelectedItem("-");
        Kie_Tanda_Gejala_Penyakit.setSelectedItem("-");
        NipTanda.setText("-");
        NmTanda.setText("-");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Cara_Penggunaan_Obat.setSelectedItem("-");
        Kie_Cara_Penggunaan_Obat.setSelectedItem("-");
        NmCaraPenggunaan.setText("-");
        NipCaraPenggunaan.setText("-");// TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        Ren_Tindakan_Medis.setSelectedItem("-");
        Kie_Ren_Tindakan_Medis.setSelectedItem("-");
        NipRenMedis.setText("-");
        NmRenMedis.setText("-");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        Pengobatan_Diberikan.setSelectedItem("-");
        Kie_Pengobatan_Diberikan.setSelectedItem("-");
        NmPengobatan.setText("-");
        NipPengobatan.setText("-");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        Proses_Perawatan.setSelectedItem("-");
        Kie_Proses_Perawatan.setSelectedItem("-");
        NmProsesRawat.setText("-");
        NipProsesRawat.setText("-");
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        Alternatif_Pengobatan.setSelectedItem("-");
        Kie_Alternatif_Pengobatan.setSelectedItem("-");
        NmAltPengobatan.setText("-");
        NipAltPengobatan.setText("-");
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        Penggunaan_Alat_Medis.setSelectedItem("-");
        Kie_Penggunaan_Alat_Medis.setSelectedItem("-");
        NipPenggunaanAlatMedis.setText("-");
        NmPenggunaanAlatMedis.setText("-");// TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        Diet_Nutrisi.setSelectedItem("-");
        Kie_Diet_Nutrisi.setSelectedItem("-");
        NmDiet.setText("-");
        NipDiet.setText("-");
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        Efek_Samping_Obat.setSelectedItem("-");
        Kie_Efek_Samping_Obat.setSelectedItem("-");
        NmEfek.setText("-");
        NipEfek.setText("-");
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        Aktivitas_Mobilisasi.setSelectedItem("-");
        Kie_Aktivitas_Mobilisasi.setSelectedItem("-");
        NmMobilisasi.setText("-");
        NipMobilisasi.setText("-");
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        Perawatan_Luka.setSelectedItem("-");
        Kie_Perawatan_Luka.setSelectedItem("-");
        NmPerawatanLuka.setText("-");
        NipPerawatanLuka.setText("-");
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        Dosis_Jadwal_Obat.setSelectedItem("-");
        Kie_Dosis_Jadwal_Obat.setSelectedItem("-");
        NmDosis.setText("-");
        NipDosis.setText("-");
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        Lain_Lain.setText("");
        Kie_Lain_Lain.setSelectedItem("-");
        NmLainLain.setText("-");
        NipLainLain.setText("-");
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        Kebersihan_Diri.setSelectedItem("-");
        Kie_Kebersihan_Diri.setSelectedItem("-");
        NmKebersihan.setText("-");
        NipKebersihan.setText("-");
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        Manajemen_Nyeri.setSelectedItem("-");
        Kie_Manajemen_Nyeri.setSelectedItem("-");
        NmManajemenNyeri.setText("-");
        NipManajemenNyeri.setText("-");
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        Penundaan_Pelayanan.setSelectedItem("-");
        Kie_Penundaan_Pelayanan.setSelectedItem("-");
        NmPenundaan.setText("-");
        NipPenundaan.setText("-");
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        Hambatan_Pelayanan.setSelectedItem("-");
        Kie_Hambatan_Pelayanan.setSelectedItem("-");
        NmHambatan.setText("-");
        NipHambatan.setText("-");
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        Pencegahan_Komplikasi.setSelectedItem("-");
        Kie_Pencegahan_Komplikasi.setSelectedItem("-");
        NmPencegahanKomplikasi.setText("-");
        NipPencegahanKomplikasi.setText("-");
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        Pencegahan_Resiko_Jatuh.setSelectedItem("-");
        Kie_Pencegahan_Resiko_Jatuh.setSelectedItem("-");
        NmResikoJatuh.setText("-");
        NipResikoJatuh.setText("-");
    }//GEN-LAST:event_jButton20ActionPerformed

    private void Kie_Lain_Lain1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Kie_Lain_Lain1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Kie_Lain_Lain1ActionPerformed

    private void Kie_Lain_Lain1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Kie_Lain_Lain1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Kie_Lain_Lain1KeyPressed

    private void BtnDokter20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokter20ActionPerformed
        if (pegawai == null || !pegawai.isDisplayable()) {
            pegawai = new DlgCariPegawai(null, false);
            pegawai.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            pegawai.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    if (pegawai.getTable().getSelectedRow() != -1) {
                        NipLainLain1.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 0).toString());
                        NmLainLain1.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 1).toString());
                    }
                    BtnDokter20.requestFocus();
                    pegawai = null;
                }
            });
            pegawai.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
            pegawai.setLocationRelativeTo(internalFrame1);
        }
        if (pegawai == null) {
            return;
        }
//        petugas.isCek();
        if (pegawai.isVisible()) {
            pegawai.toFront();
            return;
        }
        pegawai.setVisible(true);        // TODO add your handling code here:        // TODO add your handling code here:
    }//GEN-LAST:event_BtnDokter20ActionPerformed

    private void BtnDokter20KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokter20KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnDokter20KeyPressed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        Lain_Lain1.setText("");
        Kie_Lain_Lain1.setSelectedItem("-");
        NmLainLain1.setText("-");
        NipLainLain1.setText("-");
    }//GEN-LAST:event_jButton21ActionPerformed

    private void Kie_Lain_Lain2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Kie_Lain_Lain2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Kie_Lain_Lain2ActionPerformed

    private void Kie_Lain_Lain2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Kie_Lain_Lain2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Kie_Lain_Lain2KeyPressed

    private void BtnDokter21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokter21ActionPerformed
        if (pegawai == null || !pegawai.isDisplayable()) {
            pegawai = new DlgCariPegawai(null, false);
            pegawai.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            pegawai.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    if (pegawai.getTable().getSelectedRow() != -1) {
                        NipLainLain2.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 0).toString());
                        NmLainLain2.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 1).toString());
                    }
                    BtnDokter21.requestFocus();
                    pegawai = null;
                }
            });
            pegawai.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
            pegawai.setLocationRelativeTo(internalFrame1);
        }
        if (pegawai == null) {
            return;
        }
//        petugas.isCek();
        if (pegawai.isVisible()) {
            pegawai.toFront();
            return;
        }
        pegawai.setVisible(true);          // TODO add your handling code here:
    }//GEN-LAST:event_BtnDokter21ActionPerformed

    private void BtnDokter21KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokter21KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnDokter21KeyPressed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        Lain_Lain2.setText("");
        Kie_Lain_Lain2.setSelectedItem("-");
        NmLainLain2.setText("-");
        NipLainLain2.setText("-");
    }//GEN-LAST:event_jButton22ActionPerformed

    private void Kie_Lain_Lain3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Kie_Lain_Lain3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Kie_Lain_Lain3ActionPerformed

    private void Kie_Lain_Lain3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Kie_Lain_Lain3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Kie_Lain_Lain3KeyPressed

    private void BtnDokter22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokter22ActionPerformed
        if (pegawai == null || !pegawai.isDisplayable()) {
            pegawai = new DlgCariPegawai(null, false);
            pegawai.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            pegawai.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    if (pegawai.getTable().getSelectedRow() != -1) {
                        NipLainLain3.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 0).toString());
                        NmLainLain3.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 1).toString());
                    }
                    BtnDokter22.requestFocus();
                    pegawai = null;
                }
            });
            pegawai.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
            pegawai.setLocationRelativeTo(internalFrame1);
        }
        if (pegawai == null) {
            return;
        }
//        petugas.isCek();
        if (pegawai.isVisible()) {
            pegawai.toFront();
            return;
        }
        pegawai.setVisible(true);          // TODO add your handling code here:
    }//GEN-LAST:event_BtnDokter22ActionPerformed

    private void BtnDokter22KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokter22KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnDokter22KeyPressed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        Lain_Lain3.setText("");
        Kie_Lain_Lain3.setSelectedItem("-");
        NmLainLain3.setText("-");
        NipLainLain3.setText("-");
    }//GEN-LAST:event_jButton23ActionPerformed

    private void BtnDokter23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokter23ActionPerformed
        Lain_Lain3.setText(
                "1. HAK DAN KEWAJIBAN PASIEN\n"
                + "2. ALUR DAN AKSES PELAYANAN\n"
                + "3. PELAYANAN ROHANI\n"
                + "4. PELAYANAN PRIVASI\n"
                + "5. TANGGUNG JAWAB RUMAH SAKIT TERHADAP BARANG PASIEN\n"
                + "6. PEMBIAYAAN SELAMA DI RUMAH SAKIT\n"
                + "7. ALUR PENGADUAN DI RUMAH SAKIT\n"
                + "8. PROGRAM PIRATA"
        );
    }//GEN-LAST:event_BtnDokter23ActionPerformed

    private void BtnDokter23KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokter23KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnDokter23KeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMPelaksanaanInformasiEdukasiModif dialog = new RMPelaksanaanInformasiEdukasiModif(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private widget.ComboBox Aktivitas_Mobilisasi;
    private widget.ComboBox Alternatif_Pengobatan;
    private widget.Button BtnAll;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnDokter;
    private widget.Button BtnDokter1;
    private widget.Button BtnDokter10;
    private widget.Button BtnDokter11;
    private widget.Button BtnDokter12;
    private widget.Button BtnDokter13;
    private widget.Button BtnDokter14;
    private widget.Button BtnDokter15;
    private widget.Button BtnDokter16;
    private widget.Button BtnDokter17;
    private widget.Button BtnDokter18;
    private widget.Button BtnDokter19;
    private widget.Button BtnDokter2;
    private widget.Button BtnDokter20;
    private widget.Button BtnDokter21;
    private widget.Button BtnDokter22;
    private widget.Button BtnDokter23;
    private widget.Button BtnDokter3;
    private widget.Button BtnDokter4;
    private widget.Button BtnDokter5;
    private widget.Button BtnDokter6;
    private widget.Button BtnDokter7;
    private widget.Button BtnDokter8;
    private widget.Button BtnDokter9;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnRefreshPhoto1;
    private widget.Button BtnSimpan;
    private widget.ComboBox Cara_Penggunaan_Obat;
    private widget.CekBox ChkAccor;
    private widget.CekBox ChkKejadian;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.ComboBox Detik;
    private widget.ComboBox Diagnosis_Penyakit;
    private widget.ComboBox Diet_Nutrisi;
    private widget.ComboBox Dosis_Jadwal_Obat;
    private widget.ComboBox Efek_Samping_Obat;
    private widget.PanelBiasa FormInput;
    private widget.PanelBiasa FormPass3;
    private widget.PanelBiasa FormPhoto;
    private widget.ComboBox Hambatan_Pelayanan;
    private widget.ComboBox Jam;
    private widget.TextBox Jk;
    private widget.ComboBox Kebersihan_Diri;
    private widget.ComboBox Kie_Aktivitas_Mobilisasi;
    private widget.ComboBox Kie_Alternatif_Pengobatan;
    private widget.ComboBox Kie_Cara_Penggunaan_Obat;
    private widget.ComboBox Kie_Diagnosis_Penyakit;
    private widget.ComboBox Kie_Diet_Nutrisi;
    private widget.ComboBox Kie_Dosis_Jadwal_Obat;
    private widget.ComboBox Kie_Efek_Samping_Obat;
    private widget.ComboBox Kie_Hambatan_Pelayanan;
    private widget.ComboBox Kie_Kebersihan_Diri;
    private widget.ComboBox Kie_Lain_Lain;
    private widget.ComboBox Kie_Lain_Lain1;
    private widget.ComboBox Kie_Lain_Lain2;
    private widget.ComboBox Kie_Lain_Lain3;
    private widget.ComboBox Kie_Manajemen_Nyeri;
    private widget.ComboBox Kie_Pencegahan_Komplikasi;
    private widget.ComboBox Kie_Pencegahan_Resiko_Jatuh;
    private widget.ComboBox Kie_Penggunaan_Alat_Medis;
    private widget.ComboBox Kie_Pengobatan_Diberikan;
    private widget.ComboBox Kie_Penundaan_Pelayanan;
    private widget.ComboBox Kie_Perawatan_Luka;
    private widget.ComboBox Kie_Proses_Perawatan;
    private widget.ComboBox Kie_Ren_Tindakan_Medis;
    private widget.ComboBox Kie_Tanda_Gejala_Penyakit;
    private widget.Label LCount;
    private javax.swing.JTextArea Lain_Lain;
    private javax.swing.JTextArea Lain_Lain1;
    private javax.swing.JTextArea Lain_Lain2;
    private javax.swing.JTextArea Lain_Lain3;
    private widget.editorpane LoadHTML;
    private widget.editorpane LoadHTML2;
    private widget.ComboBox Manajemen_Nyeri;
    private widget.ComboBox Menit;
    private javax.swing.JMenuItem MnCetakKie;
    private widget.TextBox NipAltPengobatan;
    private widget.TextBox NipCaraPenggunaan;
    private widget.TextBox NipDiagnosa;
    private widget.TextBox NipDiet;
    private widget.TextBox NipDosis;
    private widget.TextBox NipEfek;
    private widget.TextBox NipHambatan;
    private widget.TextBox NipKebersihan;
    private widget.TextBox NipLainLain;
    private widget.TextBox NipLainLain1;
    private widget.TextBox NipLainLain2;
    private widget.TextBox NipLainLain3;
    private widget.TextBox NipManajemenNyeri;
    private widget.TextBox NipMobilisasi;
    private widget.TextBox NipPencegahanKomplikasi;
    private widget.TextBox NipPenggunaanAlatMedis;
    private widget.TextBox NipPengobatan;
    private widget.TextBox NipPenundaan;
    private widget.TextBox NipPerawatanLuka;
    private widget.TextBox NipProsesRawat;
    private widget.TextBox NipRenMedis;
    private widget.TextBox NipResikoJatuh;
    private widget.TextBox NipTanda;
    private widget.TextBox NmAltPengobatan;
    private widget.TextBox NmCaraPenggunaan;
    private widget.TextBox NmDiagnosa;
    private widget.TextBox NmDiet;
    private widget.TextBox NmDosis;
    private widget.TextBox NmEfek;
    private widget.TextBox NmHambatan;
    private widget.TextBox NmKebersihan;
    private widget.TextBox NmLainLain;
    private widget.TextBox NmLainLain1;
    private widget.TextBox NmLainLain2;
    private widget.TextBox NmLainLain3;
    private widget.TextBox NmManajemenNyeri;
    private widget.TextBox NmMobilisasi;
    private widget.TextBox NmPencegahanKomplikasi;
    private widget.TextBox NmPenggunaanAlatMedis;
    private widget.TextBox NmPengobatan;
    private widget.TextBox NmPenundaan;
    private widget.TextBox NmPerawatanLuka;
    private widget.TextBox NmProsesRawat;
    private widget.TextBox NmRenMedis;
    private widget.TextBox NmResikoJatuh;
    private widget.TextBox NmTanda;
    private widget.PanelBiasa PanelAccor;
    private widget.ComboBox Pencegahan_Komplikasi;
    private widget.ComboBox Pencegahan_Resiko_Jatuh;
    private widget.ComboBox Penggunaan_Alat_Medis;
    private widget.ComboBox Pengobatan_Diberikan;
    private widget.ComboBox Penundaan_Pelayanan;
    private widget.ComboBox Perawatan_Luka;
    private widget.ComboBox Proses_Perawatan;
    private widget.ComboBox Ren_Tindakan_Medis;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll5;
    private widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private javax.swing.JTabbedPane TabRawat;
    private widget.ComboBox Tanda_Gejala_Penyakit;
    private widget.TextBox TanggalRegistrasi;
    private widget.Tanggal TglEdukasi;
    private widget.TextBox TglLahir;
    private widget.Button btnAmbil;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel22;
    private widget.Label jLabel23;
    private widget.Label jLabel24;
    private widget.Label jLabel25;
    private widget.Label jLabel26;
    private widget.Label jLabel27;
    private widget.Label jLabel28;
    private widget.Label jLabel29;
    private widget.Label jLabel30;
    private widget.Label jLabel31;
    private widget.Label jLabel32;
    private widget.Label jLabel33;
    private widget.Label jLabel34;
    private widget.Label jLabel35;
    private widget.Label jLabel36;
    private widget.Label jLabel37;
    private widget.Label jLabel38;
    private widget.Label jLabel39;
    private widget.Label jLabel40;
    private widget.Label jLabel41;
    private widget.Label jLabel42;
    private widget.Label jLabel43;
    private widget.Label jLabel44;
    private widget.Label jLabel45;
    private widget.Label jLabel46;
    private widget.Label jLabel47;
    private widget.Label jLabel48;
    private widget.Label jLabel49;
    private widget.Label jLabel50;
    private widget.Label jLabel51;
    private widget.Label jLabel52;
    private widget.Label jLabel53;
    private widget.Label jLabel54;
    private widget.Label jLabel55;
    private widget.Label jLabel56;
    private widget.Label jLabel57;
    private widget.Label jLabel58;
    private widget.Label jLabel59;
    private widget.Label jLabel6;
    private widget.Label jLabel60;
    private widget.Label jLabel61;
    private widget.Label jLabel62;
    private widget.Label jLabel63;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator14;
    private widget.Label label11;
    private widget.Label label15;
    private widget.Label label16;
    private widget.Label label17;
    private widget.Label label18;
    private widget.Label label19;
    private widget.Label label20;
    private widget.Label label21;
    private widget.Label label22;
    private widget.Label label23;
    private widget.Label label24;
    private widget.Label label25;
    private widget.Label label26;
    private widget.Label label27;
    private widget.Label label28;
    private widget.Label label29;
    private widget.Label label30;
    private widget.Label label31;
    private widget.Label label32;
    private widget.Label label33;
    private widget.Label label34;
    private widget.Label label35;
    private widget.Label label36;
    private widget.Label label37;
    private widget.Label label38;
    private widget.Label label39;
    private widget.Label label40;
    private widget.Label label41;
    private widget.Label label42;
    private widget.Label label43;
    private widget.Label label44;
    private widget.Label label45;
    private widget.Label label46;
    private widget.Label label47;
    private widget.Label label48;
    private widget.Label label49;
    private widget.Label label50;
    private widget.Label label51;
    private widget.Label label52;
    private widget.Label label53;
    private widget.Label label54;
    private widget.Label label55;
    private widget.Label label56;
    private widget.Label label57;
    private widget.Label label58;
    private widget.Label label59;
    private widget.Label label60;
    private widget.Label label61;
    private widget.Label label62;
    private widget.Label label63;
    private widget.Label label64;
    private widget.Label label65;
    private widget.Label label66;
    private widget.Label label67;
    private widget.Label label68;
    private widget.Label label69;
    private widget.Label label70;
    private widget.Label label71;
    private widget.Label label72;
    private widget.Label label73;
    private widget.Label label74;
    private widget.Label label75;
    private widget.Label label76;
    private widget.Label label77;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollInput;
    private widget.Table tbObat;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        Valid.tabelKosong(tabMode);

        try {
            if (TCari.getText().trim().equals("")) {

                ps = koneksi.prepareStatement(
                        "SELECT "
                        + "reg_periksa.no_rawat, "
                        + "pasien.no_rkm_medis, "
                        + "pasien.nm_pasien, "
                        + "pasien.tgl_lahir, "
                        + "IF(pasien.jk='L','Laki-Laki','Perempuan') AS jk, "
                        + "pelaksanaan_kie.tanggal, "
                        + "pelaksanaan_kie.diagnosis_penyakit, "
                        + "pelaksanaan_kie.kie_diagnosis_penyakit, "
                        + "pelaksanaan_kie.nip_diagnosa, "
                        + "pg1.nama AS petugas_diagnosa, "
                        + "pelaksanaan_kie.tanda_gejala_penyakit, "
                        + "pelaksanaan_kie.kie_tanda_gejala_penyakit, "
                        + "pelaksanaan_kie.nip_tanda, "
                        + "pg2.nama AS petugas_tanda, "
                        + "pelaksanaan_kie.ren_tindakan_medis, "
                        + "pelaksanaan_kie.kie_ren_tindakan_medis, "
                        + "pelaksanaan_kie.nip_ren_medis, "
                        + "pg3.nama AS petugas_ren_medis, "
                        + "pelaksanaan_kie.pengobatan_diberikan, "
                        + "pelaksanaan_kie.kie_pengobatan_diberikan, "
                        + "pelaksanaan_kie.nip_pengobatan, "
                        + "pg4.nama AS petugas_pengobatan, "
                        + "pelaksanaan_kie.proses_perawatan, "
                        + "pelaksanaan_kie.kie_proses_perawatan, "
                        + "pelaksanaan_kie.nip_proses_rawat, "
                        + "pg5.nama AS petugas_proses_rawat, "
                        + "pelaksanaan_kie.alternatif_pengobatan, "
                        + "pelaksanaan_kie.kie_alternatif_pengobatan, "
                        + "pelaksanaan_kie.nip_alt_pengobatan, "
                        + "pg6.nama AS petugas_alt_pengobatan, "
                        + "pelaksanaan_kie.cara_penggunaan_obat, "
                        + "pelaksanaan_kie.kie_cara_penggunaan_obat, "
                        + "pelaksanaan_kie.nip_cara_penggunaan, "
                        + "pg7.nama AS petugas_cara_penggunaan, "
                        + "pelaksanaan_kie.dosis_jadwal_obat, "
                        + "pelaksanaan_kie.kie_dosis_jadwal_obat, "
                        + "pelaksanaan_kie.nip_dosis, "
                        + "pg8.nama AS petugas_dosis, "
                        + "pelaksanaan_kie.efek_samping_obat, "
                        + "pelaksanaan_kie.kie_efek_samping_obat, "
                        + "pelaksanaan_kie.nip_efek, "
                        + "pg9.nama AS petugas_efek, "
                        + "pelaksanaan_kie.diet_nutrisi, "
                        + "pelaksanaan_kie.kie_diet_nutrisi, "
                        + "pelaksanaan_kie.nip_diet, "
                        + "pg10.nama AS petugas_diet, "
                        + "pelaksanaan_kie.aktivitas_mobilisasi, "
                        + "pelaksanaan_kie.kie_aktivitas_mobilisasi, "
                        + "pelaksanaan_kie.nip_mobilisasi, "
                        + "pg11.nama AS petugas_mobilisasi, "
                        + "pelaksanaan_kie.perawatan_luka, "
                        + "pelaksanaan_kie.kie_perawatan_luka, "
                        + "pelaksanaan_kie.nip_perawatan_luka, "
                        + "pg12.nama AS petugas_perawatan_luka, "
                        + "pelaksanaan_kie.penggunaan_alat_medis, "
                        + "pelaksanaan_kie.kie_penggunaan_alat_medis, "
                        + "pelaksanaan_kie.nip_penggunaan_alat_medis, "
                        + "pg13.nama AS petugas_penggunaan_alat, "
                        + "pelaksanaan_kie.pencegahan_komplikasi, "
                        + "pelaksanaan_kie.kie_pencegahan_komplikasi, "
                        + "pelaksanaan_kie.nip_pencegahan_komplikasi, "
                        + "pg14.nama AS petugas_pencegahan_komplikasi, "
                        + "pelaksanaan_kie.manajemen_nyeri, "
                        + "pelaksanaan_kie.kie_manajemen_nyeri, "
                        + "pelaksanaan_kie.nip_manajemen_nyeri, "
                        + "pg15.nama AS petugas_manajemen_nyeri, "
                        + "pelaksanaan_kie.kebersihan_diri, "
                        + "pelaksanaan_kie.kie_kebersihan_diri, "
                        + "pelaksanaan_kie.nip_kebersihan, "
                        + "pg16.nama AS petugas_kebersihan, "
                        + "pelaksanaan_kie.penundaan_pelayanan, "
                        + "pelaksanaan_kie.kie_penundaan_pelayanan, "
                        + "pelaksanaan_kie.nip_penundaan, "
                        + "pg17.nama AS petugas_penundaan, "
                        + "pelaksanaan_kie.hambatan_pelayanan, "
                        + "pelaksanaan_kie.kie_hambatan_pelayanan, "
                        + "pelaksanaan_kie.nip_hambatan, "
                        + "pg18.nama AS petugas_hambatan, "
                        + "pelaksanaan_kie.pencegahan_resiko_jatuh, "
                        + "pelaksanaan_kie.kie_pencegahan_resiko_jatuh, "
                        + "pelaksanaan_kie.nip_resiko_jatuh, "
                        + "pg19.nama AS petugas_resiko_jatuh, "
                        + "pelaksanaan_kie.lain_lain, "
                        + "pelaksanaan_kie.kie_lain_lain, "
                        + "pelaksanaan_kie.nip_lain_lain, "
                        + "pg20.nama AS petugas_lain_lain, "
                        + "pelaksanaan_kie.lain_lain1, "
                        + "pelaksanaan_kie.kie_lain_lain1, "
                        + "pelaksanaan_kie.nip_lain_lain1, "
                        + "pg21.nama AS petugas_lain_lain1, "
                        + "pelaksanaan_kie.lain_lain2, "
                        + "pelaksanaan_kie.kie_lain_lain2, "
                        + "pelaksanaan_kie.nip_lain_lain2, "
                        + "pg22.nama AS petugas_lain_lain2, "
                        + "pelaksanaan_kie.lain_lain3, "
                        + "pelaksanaan_kie.kie_lain_lain3, "
                        + "pelaksanaan_kie.nip_lain_lain3, "
                        + "pg23.nama AS petugas_lain_lain3 "
                        + "FROM reg_periksa "
                        + "INNER JOIN pasien ON reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                        + "INNER JOIN pelaksanaan_kie ON reg_periksa.no_rawat=pelaksanaan_kie.no_rawat "
                        + "LEFT JOIN pegawai pg1 ON pg1.nik=pelaksanaan_kie.nip_diagnosa "
                        + "LEFT JOIN pegawai pg2 ON pg2.nik=pelaksanaan_kie.nip_tanda "
                        + "LEFT JOIN pegawai pg3 ON pg3.nik=pelaksanaan_kie.nip_ren_medis "
                        + "LEFT JOIN pegawai pg4 ON pg4.nik=pelaksanaan_kie.nip_pengobatan "
                        + "LEFT JOIN pegawai pg5 ON pg5.nik=pelaksanaan_kie.nip_proses_rawat "
                        + "LEFT JOIN pegawai pg6 ON pg6.nik=pelaksanaan_kie.nip_alt_pengobatan "
                        + "LEFT JOIN pegawai pg7 ON pg7.nik=pelaksanaan_kie.nip_cara_penggunaan "
                        + "LEFT JOIN pegawai pg8 ON pg8.nik=pelaksanaan_kie.nip_dosis "
                        + "LEFT JOIN pegawai pg9 ON pg9.nik=pelaksanaan_kie.nip_efek "
                        + "LEFT JOIN pegawai pg10 ON pg10.nik=pelaksanaan_kie.nip_diet "
                        + "LEFT JOIN pegawai pg11 ON pg11.nik=pelaksanaan_kie.nip_mobilisasi "
                        + "LEFT JOIN pegawai pg12 ON pg12.nik=pelaksanaan_kie.nip_perawatan_luka "
                        + "LEFT JOIN pegawai pg13 ON pg13.nik=pelaksanaan_kie.nip_penggunaan_alat_medis "
                        + "LEFT JOIN pegawai pg14 ON pg14.nik=pelaksanaan_kie.nip_pencegahan_komplikasi "
                        + "LEFT JOIN pegawai pg15 ON pg15.nik=pelaksanaan_kie.nip_manajemen_nyeri "
                        + "LEFT JOIN pegawai pg16 ON pg16.nik=pelaksanaan_kie.nip_kebersihan "
                        + "LEFT JOIN pegawai pg17 ON pg17.nik=pelaksanaan_kie.nip_penundaan "
                        + "LEFT JOIN pegawai pg18 ON pg18.nik=pelaksanaan_kie.nip_hambatan "
                        + "LEFT JOIN pegawai pg19 ON pg19.nik=pelaksanaan_kie.nip_resiko_jatuh "
                        + "LEFT JOIN pegawai pg20 ON pg20.nik=pelaksanaan_kie.nip_lain_lain "
                        + "LEFT JOIN pegawai pg21 ON pg21.nik=pelaksanaan_kie.nip_lain_lain1 "
                        + "LEFT JOIN pegawai pg22 ON pg22.nik=pelaksanaan_kie.nip_lain_lain2 "
                        + "LEFT JOIN pegawai pg23 ON pg23.nik=pelaksanaan_kie.nip_lain_lain3 "
                        + "WHERE pelaksanaan_kie.tanggal BETWEEN ? AND ? "
                        + "ORDER BY pelaksanaan_kie.tanggal"
                );
            } else {

                ps = koneksi.prepareStatement(
                        "SELECT "
                        + "reg_periksa.no_rawat, "
                        + "pasien.no_rkm_medis, "
                        + "pasien.nm_pasien, "
                        + "pasien.tgl_lahir, "
                        + "IF(pasien.jk='L','Laki-Laki','Perempuan') AS jk, "
                        + "pelaksanaan_kie.tanggal, "
                        + "pelaksanaan_kie.diagnosis_penyakit, "
                        + "pelaksanaan_kie.kie_diagnosis_penyakit, "
                        + "pelaksanaan_kie.nip_diagnosa, "
                        + "pg1.nama AS petugas_diagnosa, "
                        + "pelaksanaan_kie.tanda_gejala_penyakit, "
                        + "pelaksanaan_kie.kie_tanda_gejala_penyakit, "
                        + "pelaksanaan_kie.nip_tanda, "
                        + "pg2.nama AS petugas_tanda, "
                        + "pelaksanaan_kie.ren_tindakan_medis, "
                        + "pelaksanaan_kie.kie_ren_tindakan_medis, "
                        + "pelaksanaan_kie.nip_ren_medis, "
                        + "pg3.nama AS petugas_ren_medis, "
                        + "pelaksanaan_kie.pengobatan_diberikan, "
                        + "pelaksanaan_kie.kie_pengobatan_diberikan, "
                        + "pelaksanaan_kie.nip_pengobatan, "
                        + "pg4.nama AS petugas_pengobatan, "
                        + "pelaksanaan_kie.proses_perawatan, "
                        + "pelaksanaan_kie.kie_proses_perawatan, "
                        + "pelaksanaan_kie.nip_proses_rawat, "
                        + "pg5.nama AS petugas_proses_rawat, "
                        + "pelaksanaan_kie.alternatif_pengobatan, "
                        + "pelaksanaan_kie.kie_alternatif_pengobatan, "
                        + "pelaksanaan_kie.nip_alt_pengobatan, "
                        + "pg6.nama AS petugas_alt_pengobatan, "
                        + "pelaksanaan_kie.cara_penggunaan_obat, "
                        + "pelaksanaan_kie.kie_cara_penggunaan_obat, "
                        + "pelaksanaan_kie.nip_cara_penggunaan, "
                        + "pg7.nama AS petugas_cara_penggunaan, "
                        + "pelaksanaan_kie.dosis_jadwal_obat, "
                        + "pelaksanaan_kie.kie_dosis_jadwal_obat, "
                        + "pelaksanaan_kie.nip_dosis, "
                        + "pg8.nama AS petugas_dosis, "
                        + "pelaksanaan_kie.efek_samping_obat, "
                        + "pelaksanaan_kie.kie_efek_samping_obat, "
                        + "pelaksanaan_kie.nip_efek, "
                        + "pg9.nama AS petugas_efek, "
                        + "pelaksanaan_kie.diet_nutrisi, "
                        + "pelaksanaan_kie.kie_diet_nutrisi, "
                        + "pelaksanaan_kie.nip_diet, "
                        + "pg10.nama AS petugas_diet, "
                        + "pelaksanaan_kie.aktivitas_mobilisasi, "
                        + "pelaksanaan_kie.kie_aktivitas_mobilisasi, "
                        + "pelaksanaan_kie.nip_mobilisasi, "
                        + "pg11.nama AS petugas_mobilisasi, "
                        + "pelaksanaan_kie.perawatan_luka, "
                        + "pelaksanaan_kie.kie_perawatan_luka, "
                        + "pelaksanaan_kie.nip_perawatan_luka, "
                        + "pg12.nama AS petugas_perawatan_luka, "
                        + "pelaksanaan_kie.penggunaan_alat_medis, "
                        + "pelaksanaan_kie.kie_penggunaan_alat_medis, "
                        + "pelaksanaan_kie.nip_penggunaan_alat_medis, "
                        + "pg13.nama AS petugas_penggunaan_alat, "
                        + "pelaksanaan_kie.pencegahan_komplikasi, "
                        + "pelaksanaan_kie.kie_pencegahan_komplikasi, "
                        + "pelaksanaan_kie.nip_pencegahan_komplikasi, "
                        + "pg14.nama AS petugas_pencegahan_komplikasi, "
                        + "pelaksanaan_kie.manajemen_nyeri, "
                        + "pelaksanaan_kie.kie_manajemen_nyeri, "
                        + "pelaksanaan_kie.nip_manajemen_nyeri, "
                        + "pg15.nama AS petugas_manajemen_nyeri, "
                        + "pelaksanaan_kie.kebersihan_diri, "
                        + "pelaksanaan_kie.kie_kebersihan_diri, "
                        + "pelaksanaan_kie.nip_kebersihan, "
                        + "pg16.nama AS petugas_kebersihan, "
                        + "pelaksanaan_kie.penundaan_pelayanan, "
                        + "pelaksanaan_kie.kie_penundaan_pelayanan, "
                        + "pelaksanaan_kie.nip_penundaan, "
                        + "pg17.nama AS petugas_penundaan, "
                        + "pelaksanaan_kie.hambatan_pelayanan, "
                        + "pelaksanaan_kie.kie_hambatan_pelayanan, "
                        + "pelaksanaan_kie.nip_hambatan, "
                        + "pg18.nama AS petugas_hambatan, "
                        + "pelaksanaan_kie.pencegahan_resiko_jatuh, "
                        + "pelaksanaan_kie.kie_pencegahan_resiko_jatuh, "
                        + "pelaksanaan_kie.nip_resiko_jatuh, "
                        + "pg19.nama AS petugas_resiko_jatuh, "
                        + "pelaksanaan_kie.lain_lain, "
                        + "pelaksanaan_kie.kie_lain_lain, "
                        + "pelaksanaan_kie.nip_lain_lain, "
                        + "pg20.nama AS petugas_lain_lain, "
                        + "pelaksanaan_kie.lain_lain1, "
                        + "pelaksanaan_kie.kie_lain_lain1, "
                        + "pelaksanaan_kie.nip_lain_lain1, "
                        + "pg21.nama AS petugas_lain_lain1, "
                        + "pelaksanaan_kie.lain_lain2, "
                        + "pelaksanaan_kie.kie_lain_lain2, "
                        + "pelaksanaan_kie.nip_lain_lain2, "
                        + "pg22.nama AS petugas_lain_lain2, "
                        + "pelaksanaan_kie.lain_lain3, "
                        + "pelaksanaan_kie.kie_lain_lain3, "
                        + "pelaksanaan_kie.nip_lain_lain3, "
                        + "pg23.nama AS petugas_lain_lain3 "
                        + "FROM reg_periksa "
                        + "INNER JOIN pasien ON reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                        + "INNER JOIN pelaksanaan_kie ON reg_periksa.no_rawat=pelaksanaan_kie.no_rawat "
                        + "LEFT JOIN pegawai pg1 ON pg1.nik=pelaksanaan_kie.nip_diagnosa "
                        + "LEFT JOIN pegawai pg2 ON pg2.nik=pelaksanaan_kie.nip_tanda "
                        + "LEFT JOIN pegawai pg3 ON pg3.nik=pelaksanaan_kie.nip_ren_medis "
                        + "LEFT JOIN pegawai pg4 ON pg4.nik=pelaksanaan_kie.nip_pengobatan "
                        + "LEFT JOIN pegawai pg5 ON pg5.nik=pelaksanaan_kie.nip_proses_rawat "
                        + "LEFT JOIN pegawai pg6 ON pg6.nik=pelaksanaan_kie.nip_alt_pengobatan "
                        + "LEFT JOIN pegawai pg7 ON pg7.nik=pelaksanaan_kie.nip_cara_penggunaan "
                        + "LEFT JOIN pegawai pg8 ON pg8.nik=pelaksanaan_kie.nip_dosis "
                        + "LEFT JOIN pegawai pg9 ON pg9.nik=pelaksanaan_kie.nip_efek "
                        + "LEFT JOIN pegawai pg10 ON pg10.nik=pelaksanaan_kie.nip_diet "
                        + "LEFT JOIN pegawai pg11 ON pg11.nik=pelaksanaan_kie.nip_mobilisasi "
                        + "LEFT JOIN pegawai pg12 ON pg12.nik=pelaksanaan_kie.nip_perawatan_luka "
                        + "LEFT JOIN pegawai pg13 ON pg13.nik=pelaksanaan_kie.nip_penggunaan_alat_medis "
                        + "LEFT JOIN pegawai pg14 ON pg14.nik=pelaksanaan_kie.nip_pencegahan_komplikasi "
                        + "LEFT JOIN pegawai pg15 ON pg15.nik=pelaksanaan_kie.nip_manajemen_nyeri "
                        + "LEFT JOIN pegawai pg16 ON pg16.nik=pelaksanaan_kie.nip_kebersihan "
                        + "LEFT JOIN pegawai pg17 ON pg17.nik=pelaksanaan_kie.nip_penundaan "
                        + "LEFT JOIN pegawai pg18 ON pg18.nik=pelaksanaan_kie.nip_hambatan "
                        + "LEFT JOIN pegawai pg19 ON pg19.nik=pelaksanaan_kie.nip_resiko_jatuh "
                        + "LEFT JOIN pegawai pg20 ON pg20.nik=pelaksanaan_kie.nip_lain_lain "
                        + "LEFT JOIN pegawai pg21 ON pg21.nik=pelaksanaan_kie.nip_lain_lain1 "
                        + "LEFT JOIN pegawai pg22 ON pg22.nik=pelaksanaan_kie.nip_lain_lain2 "
                        + "LEFT JOIN pegawai pg23 ON pg23.nik=pelaksanaan_kie.nip_lain_lain3 "
                        + "WHERE pelaksanaan_kie.tanggal BETWEEN ? AND ? "
                        + "AND ( reg_periksa.no_rawat LIKE ? OR pasien.no_rkm_medis LIKE ? OR pasien.nm_pasien LIKE ? ) "
                        + "ORDER BY pelaksanaan_kie.tanggal"
                );
            }

            try {

                if (TCari.getText().trim().equals("")) {

                    ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                    ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");

                } else {

                    ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                    ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                    ps.setString(3, "%" + TCari.getText() + "%");
                    ps.setString(4, "%" + TCari.getText() + "%");
                    ps.setString(5, "%" + TCari.getText() + "%");
                }

                rs = ps.executeQuery();

                while (rs.next()) {
                    tabMode.addRow(new Object[]{
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("tgl_lahir"),
                        rs.getString("jk"),
                        rs.getString("tanggal"),
                        rs.getString("diagnosis_penyakit"),
                        rs.getString("kie_diagnosis_penyakit"),
                        rs.getString("nip_diagnosa"),
                        rs.getString("petugas_diagnosa"),
                        rs.getString("tanda_gejala_penyakit"),
                        rs.getString("kie_tanda_gejala_penyakit"),
                        rs.getString("nip_tanda"),
                        rs.getString("petugas_tanda"),
                        rs.getString("ren_tindakan_medis"),
                        rs.getString("kie_ren_tindakan_medis"),
                        rs.getString("nip_ren_medis"),
                        rs.getString("petugas_ren_medis"),
                        rs.getString("pengobatan_diberikan"),
                        rs.getString("kie_pengobatan_diberikan"),
                        rs.getString("nip_pengobatan"),
                        rs.getString("petugas_pengobatan"),
                        rs.getString("proses_perawatan"),
                        rs.getString("kie_proses_perawatan"),
                        rs.getString("nip_proses_rawat"),
                        rs.getString("petugas_proses_rawat"),
                        rs.getString("alternatif_pengobatan"),
                        rs.getString("kie_alternatif_pengobatan"),
                        rs.getString("nip_alt_pengobatan"),
                        rs.getString("petugas_alt_pengobatan"),
                        rs.getString("cara_penggunaan_obat"),
                        rs.getString("kie_cara_penggunaan_obat"),
                        rs.getString("nip_cara_penggunaan"),
                        rs.getString("petugas_cara_penggunaan"),
                        rs.getString("dosis_jadwal_obat"),
                        rs.getString("kie_dosis_jadwal_obat"),
                        rs.getString("nip_dosis"),
                        rs.getString("petugas_dosis"),
                        rs.getString("efek_samping_obat"),
                        rs.getString("kie_efek_samping_obat"),
                        rs.getString("nip_efek"),
                        rs.getString("petugas_efek"),
                        rs.getString("diet_nutrisi"),
                        rs.getString("kie_diet_nutrisi"),
                        rs.getString("nip_diet"),
                        rs.getString("petugas_diet"),
                        rs.getString("aktivitas_mobilisasi"),
                        rs.getString("kie_aktivitas_mobilisasi"),
                        rs.getString("nip_mobilisasi"),
                        rs.getString("petugas_mobilisasi"),
                        rs.getString("perawatan_luka"),
                        rs.getString("kie_perawatan_luka"),
                        rs.getString("nip_perawatan_luka"),
                        rs.getString("petugas_perawatan_luka"),
                        rs.getString("penggunaan_alat_medis"),
                        rs.getString("kie_penggunaan_alat_medis"),
                        rs.getString("nip_penggunaan_alat_medis"),
                        rs.getString("petugas_penggunaan_alat"),
                        rs.getString("pencegahan_komplikasi"),
                        rs.getString("kie_pencegahan_komplikasi"),
                        rs.getString("nip_pencegahan_komplikasi"),
                        rs.getString("petugas_pencegahan_komplikasi"),
                        rs.getString("manajemen_nyeri"),
                        rs.getString("kie_manajemen_nyeri"),
                        rs.getString("nip_manajemen_nyeri"),
                        rs.getString("petugas_manajemen_nyeri"),
                        rs.getString("kebersihan_diri"),
                        rs.getString("kie_kebersihan_diri"),
                        rs.getString("nip_kebersihan"),
                        rs.getString("petugas_kebersihan"),
                        rs.getString("penundaan_pelayanan"),
                        rs.getString("kie_penundaan_pelayanan"),
                        rs.getString("nip_penundaan"),
                        rs.getString("petugas_penundaan"),
                        rs.getString("hambatan_pelayanan"),
                        rs.getString("kie_hambatan_pelayanan"),
                        rs.getString("nip_hambatan"),
                        rs.getString("petugas_hambatan"),
                        rs.getString("pencegahan_resiko_jatuh"),
                        rs.getString("kie_pencegahan_resiko_jatuh"),
                        rs.getString("nip_resiko_jatuh"),
                        rs.getString("petugas_resiko_jatuh"),
                        rs.getString("lain_lain"),
                        rs.getString("kie_lain_lain"),
                        rs.getString("nip_lain_lain"),
                        rs.getString("petugas_lain_lain"),
                        rs.getString("lain_lain1"),
                        rs.getString("kie_lain_lain1"),
                        rs.getString("nip_lain_lain1"),
                        rs.getString("petugas_lain_lain1"),
                        rs.getString("lain_lain2"),
                        rs.getString("kie_lain_lain2"),
                        rs.getString("nip_lain_lain2"),
                        rs.getString("petugas_lain_lain2"),
                        rs.getString("lain_lain3"),
                        rs.getString("kie_lain_lain3"),
                        rs.getString("nip_lain_lain3"),
                        rs.getString("petugas_lain_lain3")

                    });
                }

            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {

                if (rs != null) {
                    rs.close();
                }

                if (ps != null) {
                    ps.close();
                }
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }

        LCount.setText("" + tabMode.getRowCount());
    }

    public void emptTeks() {
        TglEdukasi.setDate(new Date());

        Diagnosis_Penyakit.setSelectedIndex(0);
        Kie_Diagnosis_Penyakit.setSelectedIndex(0);

        Tanda_Gejala_Penyakit.setSelectedIndex(0);
        Kie_Tanda_Gejala_Penyakit.setSelectedIndex(0);

        Ren_Tindakan_Medis.setSelectedIndex(0);
        Kie_Ren_Tindakan_Medis.setSelectedIndex(0);

        Pengobatan_Diberikan.setSelectedIndex(0);
        Kie_Pengobatan_Diberikan.setSelectedIndex(0);

        Proses_Perawatan.setSelectedIndex(0);
        Kie_Proses_Perawatan.setSelectedIndex(0);

        Alternatif_Pengobatan.setSelectedIndex(0);
        Kie_Alternatif_Pengobatan.setSelectedIndex(0);

        Cara_Penggunaan_Obat.setSelectedIndex(0);
        Kie_Cara_Penggunaan_Obat.setSelectedIndex(0);

        Dosis_Jadwal_Obat.setSelectedIndex(0);
        Kie_Dosis_Jadwal_Obat.setSelectedIndex(0);

        Efek_Samping_Obat.setSelectedIndex(0);
        Kie_Efek_Samping_Obat.setSelectedIndex(0);

        Diet_Nutrisi.setSelectedIndex(0);
        Kie_Diet_Nutrisi.setSelectedIndex(0);

        Aktivitas_Mobilisasi.setSelectedIndex(0);
        Kie_Aktivitas_Mobilisasi.setSelectedIndex(0);

        Perawatan_Luka.setSelectedIndex(0);
        Kie_Perawatan_Luka.setSelectedIndex(0);

        Penggunaan_Alat_Medis.setSelectedIndex(0);
        Kie_Penggunaan_Alat_Medis.setSelectedIndex(0);

        Pencegahan_Komplikasi.setSelectedIndex(0);
        Kie_Pencegahan_Komplikasi.setSelectedIndex(0);

        Manajemen_Nyeri.setSelectedIndex(0);
        Kie_Manajemen_Nyeri.setSelectedIndex(0);

        Kebersihan_Diri.setSelectedIndex(0);
        Kie_Kebersihan_Diri.setSelectedIndex(0);

        Penundaan_Pelayanan.setSelectedIndex(0);
        Kie_Penundaan_Pelayanan.setSelectedIndex(0);

        Hambatan_Pelayanan.setSelectedIndex(0);
        Kie_Hambatan_Pelayanan.setSelectedIndex(0);

        Pencegahan_Resiko_Jatuh.setSelectedIndex(0);
        Kie_Pencegahan_Resiko_Jatuh.setSelectedIndex(0);

        Lain_Lain.setText("");
        Kie_Lain_Lain.setSelectedIndex(0);

        Lain_Lain1.setText("");
        Kie_Lain_Lain1.setSelectedIndex(0);
        Lain_Lain2.setText("");
        Kie_Lain_Lain2.setSelectedIndex(0);

        Lain_Lain3.setText("");
        Kie_Lain_Lain3.setSelectedIndex(0);

    }

    public void emptNip() {
        NipDiagnosa.setText("-");
        NmDiagnosa.setText("-");

        NipTanda.setText("-");
        NmTanda.setText("-");

        NipRenMedis.setText("-");
        NmRenMedis.setText("-");

        NipPengobatan.setText("-");
        NmPengobatan.setText("-");

        NipProsesRawat.setText("-");
        NmProsesRawat.setText("-");

        NipAltPengobatan.setText("-");
        NmAltPengobatan.setText("-");

        NipCaraPenggunaan.setText("-");
        NmCaraPenggunaan.setText("-");

        NipDosis.setText("-");
        NmDosis.setText("-");

        NipEfek.setText("-");
        NmEfek.setText("-");

        NipDiet.setText("-");
        NmDiet.setText("-");

        NipMobilisasi.setText("-");
        NmMobilisasi.setText("-");

        NipPerawatanLuka.setText("-");
        NmPerawatanLuka.setText("-");

        NipPenggunaanAlatMedis.setText("-");
        NmPenggunaanAlatMedis.setText("-");

        NipPencegahanKomplikasi.setText("-");
        NmPencegahanKomplikasi.setText("-");

        NipManajemenNyeri.setText("-");
        NmManajemenNyeri.setText("-");

        NipKebersihan.setText("-");
        NmKebersihan.setText("-");

        NipPenundaan.setText("-");
        NmPenundaan.setText("-");

        NipHambatan.setText("-");
        NmHambatan.setText("-");

        NipResikoJatuh.setText("-");
        NmResikoJatuh.setText("-");

        NipLainLain.setText("-");
        NmLainLain.setText("-");

        NipLainLain1.setText("-");
        NmLainLain1.setText("-");

        NipLainLain2.setText("-");
        NmLainLain2.setText("-");

        NipLainLain3.setText("-");
        NmLainLain3.setText("-");
        TabRawat.setSelectedIndex(0);
    }

    private void getData() {
        if (tbObat.getSelectedRow() != -1) {

            TNoRw.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString());
            TNoRM.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 1).toString());
            TPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 2).toString());
            TglLahir.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 3).toString());
            Jk.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 4).toString());

            Valid.SetTgl2(TglEdukasi,
                    tbObat.getValueAt(tbObat.getSelectedRow(), 5).toString()
            );

            Diagnosis_Penyakit.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 6).toString());
            Kie_Diagnosis_Penyakit.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString());
            NipDiagnosa.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 8).toString());
            NmDiagnosa.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 9).toString());

            Tanda_Gejala_Penyakit.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 10).toString());
            Kie_Tanda_Gejala_Penyakit.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 11).toString());
            NipTanda.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 12).toString());
            NmTanda.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 13).toString());

            Ren_Tindakan_Medis.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 14).toString());
            Kie_Ren_Tindakan_Medis.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 15).toString());
            NipRenMedis.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 16).toString());
            NmRenMedis.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 17).toString());

            Pengobatan_Diberikan.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 18).toString());
            Kie_Pengobatan_Diberikan.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 19).toString());
            NipPengobatan.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 20).toString());
            NmPengobatan.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 21).toString());

            Proses_Perawatan.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 22).toString());
            Kie_Proses_Perawatan.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 23).toString());
            NipProsesRawat.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 24).toString());
            NmProsesRawat.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 25).toString());

            Alternatif_Pengobatan.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 26).toString());
            Kie_Alternatif_Pengobatan.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 27).toString());
            NipAltPengobatan.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 28).toString());
            NmAltPengobatan.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 29).toString());

            Cara_Penggunaan_Obat.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 30).toString());
            Kie_Cara_Penggunaan_Obat.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 31).toString());
            NipCaraPenggunaan.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 32).toString());
            NmCaraPenggunaan.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 33).toString());

            Dosis_Jadwal_Obat.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 34).toString());
            Kie_Dosis_Jadwal_Obat.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 35).toString());
            NipDosis.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 36).toString());
            NmDosis.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 37).toString());

            Efek_Samping_Obat.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 38).toString());
            Kie_Efek_Samping_Obat.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 39).toString());
            NipEfek.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 40).toString());
            NmEfek.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 41).toString());

            Diet_Nutrisi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 42).toString());
            Kie_Diet_Nutrisi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 43).toString());
            NipDiet.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 44).toString());
            NmDiet.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 45).toString());

            Aktivitas_Mobilisasi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 46).toString());
            Kie_Aktivitas_Mobilisasi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 47).toString());
            NipMobilisasi.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 48).toString());
            NmMobilisasi.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 49).toString());

            Perawatan_Luka.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 50).toString());
            Kie_Perawatan_Luka.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 51).toString());
            NipPerawatanLuka.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 52).toString());
            NmPerawatanLuka.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 53).toString());

            Penggunaan_Alat_Medis.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 54).toString());
            Kie_Penggunaan_Alat_Medis.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 55).toString());
            NipPenggunaanAlatMedis.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 56).toString());
            NmPenggunaanAlatMedis.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 57).toString());

            Pencegahan_Komplikasi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 58).toString());
            Kie_Pencegahan_Komplikasi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 59).toString());
            NipPencegahanKomplikasi.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 60).toString());
            NmPencegahanKomplikasi.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 61).toString());

            Manajemen_Nyeri.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 62).toString());
            Kie_Manajemen_Nyeri.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 63).toString());
            NipManajemenNyeri.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 64).toString());
            NmManajemenNyeri.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 65).toString());

            Kebersihan_Diri.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 66).toString());
            Kie_Kebersihan_Diri.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 67).toString());
            NipKebersihan.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 68).toString());
            NmKebersihan.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 69).toString());

            Penundaan_Pelayanan.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 70).toString());
            Kie_Penundaan_Pelayanan.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 71).toString());
            NipPenundaan.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 72).toString());
            NmPenundaan.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 73).toString());

            Hambatan_Pelayanan.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 74).toString());
            Kie_Hambatan_Pelayanan.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 75).toString());
            NipHambatan.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 76).toString());
            NmHambatan.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 77).toString());

            Pencegahan_Resiko_Jatuh.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 78).toString());
            Kie_Pencegahan_Resiko_Jatuh.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 79).toString());
            NipResikoJatuh.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 80).toString());
            NmResikoJatuh.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 81).toString());

            Lain_Lain.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 82).toString());
            Kie_Lain_Lain.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 83).toString());
            NipLainLain.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 84).toString());
            NmLainLain.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 85).toString());
            Lain_Lain1.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 86).toString());
            Kie_Lain_Lain1.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 87).toString());
            NipLainLain1.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 88).toString());
            NmLainLain1.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 89).toString());

            Lain_Lain2.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 90).toString());
            Kie_Lain_Lain2.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 91).toString());
            NipLainLain2.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 92).toString());
            NmLainLain2.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 93).toString());

            Lain_Lain3.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 94).toString());
            Kie_Lain_Lain3.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 95).toString());
            NipLainLain3.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 96).toString());
            NmLainLain3.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 97).toString());
        }
    }

    private void isRawat() {
        try {
            ps = koneksi.prepareStatement(
                    "select reg_periksa.no_rkm_medis,pasien.nm_pasien, if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,"
                    + "reg_periksa.tgl_registrasi,reg_periksa.jam_reg from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "where reg_periksa.no_rawat=?");
            try {
                ps.setString(1, TNoRw.getText());
                rs = ps.executeQuery();
                if (rs.next()) {
                    TNoRM.setText(rs.getString("no_rkm_medis"));
                    DTPCari1.setDate(rs.getDate("tgl_registrasi"));
                    TPasien.setText(rs.getString("nm_pasien"));
                    Jk.setText(rs.getString("jk"));
                    TglLahir.setText(rs.getString("tgl_lahir"));
                    TanggalRegistrasi.setText(rs.getString("tgl_registrasi") + " " + rs.getString("jam_reg"));
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notif : " + e);
        }
    }

    public void setNoRm(String norwt, Date tgl2) {
        TNoRw.setText(norwt);
        TCari.setText(norwt);
        DTPCari2.setDate(tgl2);
        isRawat();
    }

    public void setTglEdu(Date tgl2, String jam, String menit, String detik, Boolean Bool) {
        TglEdukasi.setDate(tgl2);
        Jam.setSelectedItem(jam);
        Menit.setSelectedItem(menit);
        Detik.setSelectedItem(detik);
        ChkKejadian.setSelected(Bool);
        isRawat();
    }

    public void isCek() {
//        BtnSimpan.setEnabled(akses.getlayanan_kedokteran_fisik_rehabilitasi());
//        BtnHapus.setEnabled(akses.getlayanan_kedokteran_fisik_rehabilitasi());
//        BtnEdit.setEnabled(akses.getlayanan_kedokteran_fisik_rehabilitasi());
//        BtnEdit.setEnabled(akses.getlayanan_kedokteran_fisik_rehabilitasi());
//        if (akses.getjml2() >= 1) {
//            NipDiagnosa.setEditable(false);
//            BtnDokter.setEnabled(false);
//            NipDiagnosa.setText(akses.getkode());
//            NmDiagnosa.setText(Sequel.CariDokter(NipDiagnosa.getText()));
//            if (NmDiagnosa.getText().equals("")) {
//                NipDiagnosa.setText("");
//                JOptionPane.showMessageDialog(null, "User login bukan dokter...!!");
//            }
//        }

        if (TANGGALMUNDUR.equals("no")) {
            if (!akses.getkode().equals("Admin Utama")) {
                TglEdukasi.setEditable(false);
                TglEdukasi.setEnabled(false);
            }
        }
    }

    public void setTampil() {
        TabRawat.setSelectedIndex(1);
    }

    private void hapus() {
        if (Sequel.queryu2tf("delete from pelaksanaan_kie where no_rawat=? and tanggal=?", 2, new String[]{
            tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString(),
            tbObat.getValueAt(tbObat.getSelectedRow(), 5).toString()
        }) == true) {
            tabMode.removeRow(tbObat.getSelectedRow());
            LCount.setText("" + tabMode.getRowCount());
            TabRawat.setSelectedIndex(1);
        } else {
            JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
        }
    }

//    ASLI
//    private void ganti() {
//        if(Sequel.mengedittf("layanan_kedokteran_fisik_rehabilitasi","no_rawat=?","no_rawat=?,tanggal=?,kd_dokter=?,pendamping=?,keterangan_pendamping=?,anamnesa=?,pemeriksaan_fisik=?,diagnosa_medis=?,diagnosa_fungsi=?,tatalaksana=?,anjuran=?,evaluasi=?,suspek_penyakit_kerja=?,keterangan_suspek_penyakit_kerja=?",15,new String[]{
//                TNoRw.getText(),Valid.SetTgl(TglAsuhan.getSelectedItem()+"")+" "+TglAsuhan.getSelectedItem().toString().substring(11,19),KdDokter.getText(),
//                Pendamping.getSelectedItem().toString(),KeteranganPendamping.getText(),Anamnesa.getText(),PemeriksaanFisik.getText(),DiagnosisMedis.getText(), 
//                DiagnosisFungsi.getText(),TatalaksanaKFR.getText(),Anjuran.getText(),Evaluasi.getText(),SuspekPenyakit.getSelectedItem().toString(), 
//                KeteranganSuspekPenyakit.getText(),tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()
//            })==true){
//                tbObat.setValueAt(TNoRw.getText(),tbObat.getSelectedRow(),0);
//                tbObat.setValueAt(TNoRM.getText(),tbObat.getSelectedRow(),1);
//                tbObat.setValueAt(TPasien.getText(),tbObat.getSelectedRow(),2);
//                tbObat.setValueAt(TglLahir.getText(),tbObat.getSelectedRow(),3);
//                tbObat.setValueAt(Jk.getText(),tbObat.getSelectedRow(),4);
//                tbObat.setValueAt(KdDokter.getText(),tbObat.getSelectedRow(),5);
//                tbObat.setValueAt(NmDokter.getText(),tbObat.getSelectedRow(),6);
//                tbObat.setValueAt(Valid.SetTgl(TglAsuhan.getSelectedItem()+"")+" "+TglAsuhan.getSelectedItem().toString().substring(11,19),tbObat.getSelectedRow(),7);
//                tbObat.setValueAt(Pendamping.getSelectedItem().toString(),tbObat.getSelectedRow(),8);
//                tbObat.setValueAt(KeteranganPendamping.getText(),tbObat.getSelectedRow(),9);
//                tbObat.setValueAt(Anamnesa.getText(),tbObat.getSelectedRow(),10);
//                tbObat.setValueAt(PemeriksaanFisik.getText(),tbObat.getSelectedRow(),11);
//                tbObat.setValueAt(DiagnosisMedis.getText(),tbObat.getSelectedRow(),12);
//                tbObat.setValueAt(DiagnosisFungsi.getText(),tbObat.getSelectedRow(),13);
//                tbObat.setValueAt(TatalaksanaKFR.getText(),tbObat.getSelectedRow(),14);
//                tbObat.setValueAt(Anjuran.getText(),tbObat.getSelectedRow(),15);
//                tbObat.setValueAt(Evaluasi.getText(),tbObat.getSelectedRow(),16);
//                tbObat.setValueAt(SuspekPenyakit.getSelectedItem().toString(),tbObat.getSelectedRow(),17);
//                tbObat.setValueAt(KeteranganSuspekPenyakit.getText(),tbObat.getSelectedRow(),18);
//                emptTeks();
//                TabRawat.setSelectedIndex(1);
//        }
//    }
    private void ganti() {
        java.time.LocalDateTime now = java.time.LocalDateTime.now();
        String waktuSekarang = now.format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        // Fetch existing waktu values before updating
        String[] existingWaktu = new String[23];
        try {
            ps = koneksi.prepareStatement(
                    "SELECT waktu_diagnosis, waktu_tanda, waktu_ren_medis, waktu_pengobatan, "
                    + "waktu_proses_rawat, waktu_alt_pengobatan, waktu_cara_pengobatan, waktu_dosis, "
                    + "waktu_efek, waktu_diet, waktu_mobilisasi, waktu_perawatan_luka, "
                    + "waktu_penggunaan_alat_medis, waktu_pencegahan_komplikasi, waktu_manajemen_nyeri, "
                    + "waktu_kebersihan, waktu_penundaan, waktu_hambatan, waktu_resiko_jatuh, waktu_lain_lain "
                    + "waktu_lain_lain, waktu_lain_lain1, waktu_lain_lain2, waktu_lain_lain3 "
                    + "FROM pelaksanaan_kie WHERE tanggal=? AND no_rawat=?"
            );
            ps.setString(1, tbObat.getValueAt(tbObat.getSelectedRow(), 5).toString());
            ps.setString(2, tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString());
            rs = ps.executeQuery();
            if (rs.next()) {
                for (int i = 0; i < 23; i++) {
                    existingWaktu[i] = rs.getString(i + 1); // may be null if not set
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Helper: if existing waktu is already set, keep it; otherwise use waktuSekarang if field is not "-"
        // Index order matches: diagnosis, tanda, ren_medis, pengobatan, proses, alt, cara, dosis,
        //                      efek, diet, aktivitas, luka, alat, komplikasi, nyeri, kebersihan,
        //                      penundaan, hambatan, resiko, lain
        String[] resolvedWaktu = new String[23];
        String[] fieldValues = {
            Diagnosis_Penyakit.getSelectedItem().toString(),
            Tanda_Gejala_Penyakit.getSelectedItem().toString(),
            Ren_Tindakan_Medis.getSelectedItem().toString(),
            Pengobatan_Diberikan.getSelectedItem().toString(),
            Proses_Perawatan.getSelectedItem().toString(),
            Alternatif_Pengobatan.getSelectedItem().toString(),
            Cara_Penggunaan_Obat.getSelectedItem().toString(),
            Dosis_Jadwal_Obat.getSelectedItem().toString(),
            Efek_Samping_Obat.getSelectedItem().toString(),
            Diet_Nutrisi.getSelectedItem().toString(),
            Aktivitas_Mobilisasi.getSelectedItem().toString(),
            Perawatan_Luka.getSelectedItem().toString(),
            Penggunaan_Alat_Medis.getSelectedItem().toString(),
            Pencegahan_Komplikasi.getSelectedItem().toString(),
            Manajemen_Nyeri.getSelectedItem().toString(),
            Kebersihan_Diri.getSelectedItem().toString(),
            Penundaan_Pelayanan.getSelectedItem().toString(),
            Hambatan_Pelayanan.getSelectedItem().toString(),
            Pencegahan_Resiko_Jatuh.getSelectedItem().toString(),
            Lain_Lain.getText(),
            Lain_Lain1.getText(),
            Lain_Lain2.getText(),
            Lain_Lain3.getText()
        };

        for (int i = 0; i < 23; i++) {
            if (existingWaktu[i] != null && !existingWaktu[i].isEmpty()) {
                // Already has a waktu — keep it
                resolvedWaktu[i] = existingWaktu[i];
            } else {
                // No waktu yet — set now only if field is not "-"
                resolvedWaktu[i]
                        = fieldValues[i].trim().isEmpty() || fieldValues[i].equals("-")
                        ? null
                        : waktuSekarang;
            }
        }

        if (Sequel.mengedittf("pelaksanaan_kie",
                "tanggal=? and no_rawat=?",
                "no_rawat=?,tanggal=?,"
                + "diagnosis_penyakit=?,kie_diagnosis_penyakit=?,nip_diagnosa=?,waktu_diagnosis=?,"
                + "tanda_gejala_penyakit=?,kie_tanda_gejala_penyakit=?,nip_tanda=?,waktu_tanda=?,"
                + "ren_tindakan_medis=?,kie_ren_tindakan_medis=?,nip_ren_medis=?,waktu_ren_medis=?,"
                + "pengobatan_diberikan=?,kie_pengobatan_diberikan=?,nip_pengobatan=?,waktu_pengobatan=?,"
                + "proses_perawatan=?,kie_proses_perawatan=?,nip_proses_rawat=?,waktu_proses_rawat=?,"
                + "alternatif_pengobatan=?,kie_alternatif_pengobatan=?,nip_alt_pengobatan=?,waktu_alt_pengobatan=?,"
                + "cara_penggunaan_obat=?,kie_cara_penggunaan_obat=?,nip_cara_penggunaan=?,waktu_cara_pengobatan=?,"
                + "dosis_jadwal_obat=?,kie_dosis_jadwal_obat=?,nip_dosis=?,waktu_dosis=?,"
                + "efek_samping_obat=?,kie_efek_samping_obat=?,nip_efek=?,waktu_efek=?,"
                + "diet_nutrisi=?,kie_diet_nutrisi=?,nip_diet=?,waktu_diet=?,"
                + "aktivitas_mobilisasi=?,kie_aktivitas_mobilisasi=?,nip_mobilisasi=?,waktu_mobilisasi=?,"
                + "perawatan_luka=?,kie_perawatan_luka=?,nip_perawatan_luka=?,waktu_perawatan_luka=?,"
                + "penggunaan_alat_medis=?,kie_penggunaan_alat_medis=?,nip_penggunaan_alat_medis=?,waktu_penggunaan_alat_medis=?,"
                + "pencegahan_komplikasi=?,kie_pencegahan_komplikasi=?,nip_pencegahan_komplikasi=?,waktu_pencegahan_komplikasi=?,"
                + "manajemen_nyeri=?,kie_manajemen_nyeri=?,nip_manajemen_nyeri=?,waktu_manajemen_nyeri=?,"
                + "kebersihan_diri=?,kie_kebersihan_diri=?,nip_kebersihan=?,waktu_kebersihan=?,"
                + "penundaan_pelayanan=?,kie_penundaan_pelayanan=?,nip_penundaan=?,waktu_penundaan=?,"
                + "hambatan_pelayanan=?,kie_hambatan_pelayanan=?,nip_hambatan=?,waktu_hambatan=?,"
                + "pencegahan_resiko_jatuh=?,kie_pencegahan_resiko_jatuh=?,nip_resiko_jatuh=?,waktu_resiko_jatuh=?,"
                + "lain_lain=?,kie_lain_lain=?,nip_lain_lain=?,waktu_lain_lain=?,"
                + "lain_lain1=?,kie_lain_lain1=?,nip_lain_lain1=?,waktu_lain_lain1=?,"
                + "lain_lain2=?,kie_lain_lain2=?,nip_lain_lain2=?,waktu_lain_lain2=?,"
                + "lain_lain3=?,kie_lain_lain3=?,nip_lain_lain3=?,waktu_lain_lain3=?",
                96,
                new String[]{
                    TNoRw.getText(),
                    Valid.SetTgl(TglEdukasi.getSelectedItem() + "") + " "
                    + Jam.getSelectedItem().toString() + ":"
                    + Menit.getSelectedItem().toString() + ":"
                    + Detik.getSelectedItem().toString(),
                    Diagnosis_Penyakit.getSelectedItem().toString(),
                    Kie_Diagnosis_Penyakit.getSelectedItem().toString(),
                    NipDiagnosa.getText(), resolvedWaktu[0],
                    Tanda_Gejala_Penyakit.getSelectedItem().toString(),
                    Kie_Tanda_Gejala_Penyakit.getSelectedItem().toString(),
                    NipTanda.getText(), resolvedWaktu[1],
                    Ren_Tindakan_Medis.getSelectedItem().toString(),
                    Kie_Ren_Tindakan_Medis.getSelectedItem().toString(),
                    NipRenMedis.getText(), resolvedWaktu[2],
                    Pengobatan_Diberikan.getSelectedItem().toString(),
                    Kie_Pengobatan_Diberikan.getSelectedItem().toString(),
                    NipPengobatan.getText(), resolvedWaktu[3],
                    Proses_Perawatan.getSelectedItem().toString(),
                    Kie_Proses_Perawatan.getSelectedItem().toString(),
                    NipProsesRawat.getText(), resolvedWaktu[4],
                    Alternatif_Pengobatan.getSelectedItem().toString(),
                    Kie_Alternatif_Pengobatan.getSelectedItem().toString(),
                    NipAltPengobatan.getText(), resolvedWaktu[5],
                    Cara_Penggunaan_Obat.getSelectedItem().toString(),
                    Kie_Cara_Penggunaan_Obat.getSelectedItem().toString(),
                    NipCaraPenggunaan.getText(), resolvedWaktu[6],
                    Dosis_Jadwal_Obat.getSelectedItem().toString(),
                    Kie_Dosis_Jadwal_Obat.getSelectedItem().toString(),
                    NipDosis.getText(), resolvedWaktu[7],
                    Efek_Samping_Obat.getSelectedItem().toString(),
                    Kie_Efek_Samping_Obat.getSelectedItem().toString(),
                    NipEfek.getText(), resolvedWaktu[8],
                    Diet_Nutrisi.getSelectedItem().toString(),
                    Kie_Diet_Nutrisi.getSelectedItem().toString(),
                    NipDiet.getText(), resolvedWaktu[9],
                    Aktivitas_Mobilisasi.getSelectedItem().toString(),
                    Kie_Aktivitas_Mobilisasi.getSelectedItem().toString(),
                    NipMobilisasi.getText(), resolvedWaktu[10],
                    Perawatan_Luka.getSelectedItem().toString(),
                    Kie_Perawatan_Luka.getSelectedItem().toString(),
                    NipPerawatanLuka.getText(), resolvedWaktu[11],
                    Penggunaan_Alat_Medis.getSelectedItem().toString(),
                    Kie_Penggunaan_Alat_Medis.getSelectedItem().toString(),
                    NipPenggunaanAlatMedis.getText(), resolvedWaktu[12],
                    Pencegahan_Komplikasi.getSelectedItem().toString(),
                    Kie_Pencegahan_Komplikasi.getSelectedItem().toString(),
                    NipPencegahanKomplikasi.getText(), resolvedWaktu[13],
                    Manajemen_Nyeri.getSelectedItem().toString(),
                    Kie_Manajemen_Nyeri.getSelectedItem().toString(),
                    NipManajemenNyeri.getText(), resolvedWaktu[14],
                    Kebersihan_Diri.getSelectedItem().toString(),
                    Kie_Kebersihan_Diri.getSelectedItem().toString(),
                    NipKebersihan.getText(), resolvedWaktu[15],
                    Penundaan_Pelayanan.getSelectedItem().toString(),
                    Kie_Penundaan_Pelayanan.getSelectedItem().toString(),
                    NipPenundaan.getText(), resolvedWaktu[16],
                    Hambatan_Pelayanan.getSelectedItem().toString(),
                    Kie_Hambatan_Pelayanan.getSelectedItem().toString(),
                    NipHambatan.getText(), resolvedWaktu[17],
                    Pencegahan_Resiko_Jatuh.getSelectedItem().toString(),
                    Kie_Pencegahan_Resiko_Jatuh.getSelectedItem().toString(),
                    NipResikoJatuh.getText(), resolvedWaktu[18],
                    Lain_Lain.getText(),
                    Kie_Lain_Lain.getSelectedItem().toString(),
                    NipLainLain.getText(), resolvedWaktu[19],
                    Lain_Lain1.getText(),
                    Kie_Lain_Lain1.getSelectedItem().toString(),
                    NipLainLain1.getText(),
                    resolvedWaktu[20],
                    Lain_Lain2.getText(),
                    Kie_Lain_Lain2.getSelectedItem().toString(),
                    NipLainLain2.getText(),
                    resolvedWaktu[21],
                    Lain_Lain3.getText(),
                    Kie_Lain_Lain3.getSelectedItem().toString(),
                    NipLainLain3.getText(),
                    resolvedWaktu[22],
                    // WHERE
                    tbObat.getValueAt(tbObat.getSelectedRow(), 5).toString(),
                    tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString()
                }
        )) {
            tampil();
            emptTeks();
        }
    }

//  Asli
//    private void simpan() {
//        if(Sequel.menyimpantf("layanan_kedokteran_fisik_rehabilitasi1","?,?,?,?,?,?,?,?,?,?,?,?,?,?,'Belum Selesai'","No.Rawat",14,new String[]{
//                TNoRw.getText(),Valid.SetTgl(TglAsuhan.getSelectedItem()+"")+" "+TglAsuhan.getSelectedItem().toString().substring(11,19),KdDokter.getText(),
//                Pendamping.getSelectedItem().toString(),KeteranganPendamping.getText(),Anamnesa.getText(),PemeriksaanFisik.getText(),DiagnosisMedis.getText(), 
//                DiagnosisFungsi.getText(),TatalaksanaKFR.getText(),Anjuran.getText(),Evaluasi.getText(),SuspekPenyakit.getSelectedItem().toString(), 
//                KeteranganSuspekPenyakit.getText()
//            })==true){
//                tabMode.addRow(new Object[]{
//                    TNoRw.getText(),TNoRM.getText(),TPasien.getText(),TglLahir.getText(),Jk.getText(),KdDokter.getText(),NmDokter.getText(),
//                    Valid.SetTgl(TglAsuhan.getSelectedItem()+"")+" "+TglAsuhan.getSelectedItem().toString().substring(11,19),Pendamping.getSelectedItem().toString(),
//                    KeteranganPendamping.getText(),Anamnesa.getText(),PemeriksaanFisik.getText(),DiagnosisMedis.getText(),DiagnosisFungsi.getText(),TatalaksanaKFR.getText(),
//                    Anjuran.getText(),Evaluasi.getText(),SuspekPenyakit.getSelectedItem().toString(),KeteranganSuspekPenyakit.getText()
//                });
//                emptTeks();
//                LCount.setText(""+tabMode.getRowCount());
//        }
//    }
    private String waktuIfNotDash(String value, String tanggal) {
        if ("-".equals(value)) {
            return null;
        }
        return tanggal + " "
                + Jam.getSelectedItem().toString() + ":"
                + Menit.getSelectedItem().toString() + ":"
                + Detik.getSelectedItem().toString();
    }

    private void simpan() {
        String tgl = Valid.SetTgl(TglEdukasi.getSelectedItem() + "");
        String tanggal = tgl + " "
                + Jam.getSelectedItem().toString() + ":"
                + Menit.getSelectedItem().toString() + ":"
                + Detik.getSelectedItem().toString();

        // Real time saat tombol simpan ditekan
        java.time.LocalDateTime now = java.time.LocalDateTime.now();
        String waktuSekarang = now.format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String placeholder = String.join(",", java.util.Collections.nCopies(94, "?"));

        if (Sequel.menyimpantf("pelaksanaan_kie",
                //                "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?",
                placeholder,
                "Data",
                94,
                new String[]{
                    TNoRw.getText(),
                    tanggal,
                    Diagnosis_Penyakit.getSelectedItem().toString(),
                    Kie_Diagnosis_Penyakit.getSelectedItem().toString(),
                    Tanda_Gejala_Penyakit.getSelectedItem().toString(),
                    Kie_Tanda_Gejala_Penyakit.getSelectedItem().toString(),
                    Ren_Tindakan_Medis.getSelectedItem().toString(),
                    Kie_Ren_Tindakan_Medis.getSelectedItem().toString(),
                    Pengobatan_Diberikan.getSelectedItem().toString(),
                    Kie_Pengobatan_Diberikan.getSelectedItem().toString(),
                    Proses_Perawatan.getSelectedItem().toString(),
                    Kie_Proses_Perawatan.getSelectedItem().toString(),
                    Alternatif_Pengobatan.getSelectedItem().toString(),
                    Kie_Alternatif_Pengobatan.getSelectedItem().toString(),
                    Cara_Penggunaan_Obat.getSelectedItem().toString(),
                    Kie_Cara_Penggunaan_Obat.getSelectedItem().toString(),
                    Dosis_Jadwal_Obat.getSelectedItem().toString(),
                    Kie_Dosis_Jadwal_Obat.getSelectedItem().toString(),
                    Efek_Samping_Obat.getSelectedItem().toString(),
                    Kie_Efek_Samping_Obat.getSelectedItem().toString(),
                    Diet_Nutrisi.getSelectedItem().toString(),
                    Kie_Diet_Nutrisi.getSelectedItem().toString(),
                    Aktivitas_Mobilisasi.getSelectedItem().toString(),
                    Kie_Aktivitas_Mobilisasi.getSelectedItem().toString(),
                    Perawatan_Luka.getSelectedItem().toString(),
                    Kie_Perawatan_Luka.getSelectedItem().toString(),
                    Penggunaan_Alat_Medis.getSelectedItem().toString(),
                    Kie_Penggunaan_Alat_Medis.getSelectedItem().toString(),
                    Pencegahan_Komplikasi.getSelectedItem().toString(),
                    Kie_Pencegahan_Komplikasi.getSelectedItem().toString(),
                    Manajemen_Nyeri.getSelectedItem().toString(),
                    Kie_Manajemen_Nyeri.getSelectedItem().toString(),
                    Kebersihan_Diri.getSelectedItem().toString(),
                    Kie_Kebersihan_Diri.getSelectedItem().toString(),
                    Penundaan_Pelayanan.getSelectedItem().toString(),
                    Kie_Penundaan_Pelayanan.getSelectedItem().toString(),
                    Hambatan_Pelayanan.getSelectedItem().toString(),
                    Kie_Hambatan_Pelayanan.getSelectedItem().toString(),
                    Pencegahan_Resiko_Jatuh.getSelectedItem().toString(),
                    Kie_Pencegahan_Resiko_Jatuh.getSelectedItem().toString(),
                    Lain_Lain.getText(),
                    Kie_Lain_Lain.getSelectedItem().toString(),
                    Lain_Lain1.getText(),
                    Kie_Lain_Lain1.getSelectedItem().toString(),
                    Lain_Lain2.getText(),
                    Kie_Lain_Lain2.getSelectedItem().toString(),
                    Lain_Lain3.getText(),
                    Kie_Lain_Lain3.getSelectedItem().toString(),
                    // NIP
                    NipDiagnosa.getText(),
                    NipTanda.getText(),
                    NipRenMedis.getText(),
                    NipPengobatan.getText(),
                    NipProsesRawat.getText(),
                    NipAltPengobatan.getText(),
                    NipCaraPenggunaan.getText(),
                    NipDosis.getText(),
                    NipEfek.getText(),
                    NipDiet.getText(),
                    NipMobilisasi.getText(),
                    NipPerawatanLuka.getText(),
                    NipPenggunaanAlatMedis.getText(),
                    NipPencegahanKomplikasi.getText(),
                    NipManajemenNyeri.getText(),
                    NipKebersihan.getText(),
                    NipPenundaan.getText(),
                    NipHambatan.getText(),
                    NipResikoJatuh.getText(),
                    NipLainLain.getText(),
                    NipLainLain1.getText(),
                    NipLainLain2.getText(),
                    NipLainLain3.getText(),
                    // Waktu real time (null if main field is "-")
                    Diagnosis_Penyakit.getSelectedItem().toString().equals("-") ? null : waktuSekarang,
                    Tanda_Gejala_Penyakit.getSelectedItem().toString().equals("-") ? null : waktuSekarang,
                    Ren_Tindakan_Medis.getSelectedItem().toString().equals("-") ? null : waktuSekarang,
                    Pengobatan_Diberikan.getSelectedItem().toString().equals("-") ? null : waktuSekarang,
                    Proses_Perawatan.getSelectedItem().toString().equals("-") ? null : waktuSekarang,
                    Alternatif_Pengobatan.getSelectedItem().toString().equals("-") ? null : waktuSekarang,
                    Cara_Penggunaan_Obat.getSelectedItem().toString().equals("-") ? null : waktuSekarang,
                    Dosis_Jadwal_Obat.getSelectedItem().toString().equals("-") ? null : waktuSekarang,
                    Efek_Samping_Obat.getSelectedItem().toString().equals("-") ? null : waktuSekarang,
                    Diet_Nutrisi.getSelectedItem().toString().equals("-") ? null : waktuSekarang,
                    Aktivitas_Mobilisasi.getSelectedItem().toString().equals("-") ? null : waktuSekarang,
                    Perawatan_Luka.getSelectedItem().toString().equals("-") ? null : waktuSekarang,
                    Penggunaan_Alat_Medis.getSelectedItem().toString().equals("-") ? null : waktuSekarang,
                    Pencegahan_Komplikasi.getSelectedItem().toString().equals("-") ? null : waktuSekarang,
                    Manajemen_Nyeri.getSelectedItem().toString().equals("-") ? null : waktuSekarang,
                    Kebersihan_Diri.getSelectedItem().toString().equals("-") ? null : waktuSekarang,
                    Penundaan_Pelayanan.getSelectedItem().toString().equals("-") ? null : waktuSekarang,
                    Hambatan_Pelayanan.getSelectedItem().toString().equals("-") ? null : waktuSekarang,
                    Pencegahan_Resiko_Jatuh.getSelectedItem().toString().equals("-") ? null : waktuSekarang,
                    Lain_Lain.getText().trim().isEmpty() || Lain_Lain.getText().equals("-")
                    ? null : waktuSekarang,
                    Lain_Lain1.getText().trim().isEmpty() || Lain_Lain1.getText().equals("-")
                    ? null : waktuSekarang,
                    Lain_Lain2.getText().trim().isEmpty() || Lain_Lain2.getText().equals("-")
                    ? null : waktuSekarang,
                    Lain_Lain3.getText().trim().isEmpty() || Lain_Lain3.getText().equals("-")
                    ? null : waktuSekarang
                }
        )) {
            tampil();
            LCount.setText("" + tabMode.getRowCount());
            emptTeks();
            emptNip();
        }
    }

    private void isPhoto() {
        if (ChkAccor.isSelected() == true) {
            ChkAccor.setVisible(false);
            PanelAccor.setPreferredSize(new Dimension(480, HEIGHT));
            FormPhoto.setVisible(true);
            ChkAccor.setVisible(true);
        } else if (ChkAccor.isSelected() == false) {
            ChkAccor.setVisible(false);
            PanelAccor.setPreferredSize(new Dimension(15, HEIGHT));
            FormPhoto.setVisible(false);
            ChkAccor.setVisible(true);
        }
    }

    private void panggilPhoto() {
        if (FormPhoto.isVisible() == true) {
            try {
                ps = koneksi.prepareStatement("select bukti_pelaksanaan_kie.photo from bukti_pelaksanaan_kie where bukti_pelaksanaan_kie.no_rawat=? and bukti_pelaksanaan_kie.tanggal=?");
                try {
                    ps.setString(1, tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString());
                    ps.setString(2, tbObat.getValueAt(tbObat.getSelectedRow(), 5).toString());
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        if (rs.getString("photo").equals("") || rs.getString("photo").equals("-")) {
                            LoadHTML2.setText("<html><body><center><br><br><font face='tahoma' size='2' color='#434343'>Kosong</font></center></body></html>");
                        } else {
                            LoadHTML2.setText("<html><body><center><img src='http://" + koneksiDB.HOSTHYBRIDWEB() + ":" + koneksiDB.PORTWEB() + "/" + koneksiDB.HYBRIDWEB() + "/pelaksanaankie/" + rs.getString("photo") + "' alt='photo' width='500' height='500'/></center></body></html>");
                        }
                    } else {
                        LoadHTML2.setText("<html><body><center><br><br><font face='tahoma' size='2' color='#434343'>Kosong</font></center></body></html>");
                    }
                } catch (Exception e) {
                    System.out.println("Notif : " + e);
                } finally {
                    if (rs != null) {
                        rs.close();
                    }
                    if (ps != null) {
                        ps.close();
                    }
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            }
        }
    }

    private void jam() {
        ActionListener taskPerformer = new ActionListener() {
            private int nilai_jam;
            private int nilai_menit;
            private int nilai_detik;

            public void actionPerformed(ActionEvent e) {
                String nol_jam = "";
                String nol_menit = "";
                String nol_detik = "";

                Date now = Calendar.getInstance().getTime();

                // Mengambil nilaj JAM, MENIT, dan DETIK Sekarang
                if (ChkKejadian.isSelected() == true) {
                    nilai_jam = now.getHours();
                    nilai_menit = now.getMinutes();
                    nilai_detik = now.getSeconds();
                } else if (ChkKejadian.isSelected() == false) {
                    nilai_jam = Jam.getSelectedIndex();
                    nilai_menit = Menit.getSelectedIndex();
                    nilai_detik = Detik.getSelectedIndex();
                }

                // Jika nilai JAM lebih kecil dari 10 (hanya 1 digit)
                if (nilai_jam <= 9) {
                    // Tambahkan "0" didepannya
                    nol_jam = "0";
                }
                // Jika nilai MENIT lebih kecil dari 10 (hanya 1 digit)
                if (nilai_menit <= 9) {
                    // Tambahkan "0" didepannya
                    nol_menit = "0";
                }
                // Jika nilai DETIK lebih kecil dari 10 (hanya 1 digit)
                if (nilai_detik <= 9) {
                    // Tambahkan "0" didepannya
                    nol_detik = "0";
                }
                // Membuat String JAM, MENIT, DETIK
                String jam = nol_jam + Integer.toString(nilai_jam);
                String menit = nol_menit + Integer.toString(nilai_menit);
                String detik = nol_detik + Integer.toString(nilai_detik);
                // Menampilkan pada Layar
                //tampil_jam.setText("  " + jam + " : " + menit + " : " + detik + "  ");
                Jam.setSelectedItem(jam);
                Menit.setSelectedItem(menit);
                Detik.setSelectedItem(detik);
            }
        };
        // Timer
        new Timer(1240, taskPerformer).start();
    }

    private void runBackground(Runnable task) {
        if (ceksukses) {
            return;
        }
        if (executor.isShutdown() || executor.isTerminated()) {
            return;
        }
        if (!isDisplayable()) {
            return;
        }

        ceksukses = true;
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        try {
            executor.submit(() -> {
                try {
                    task.run();
                } finally {
                    ceksukses = false;
                    SwingUtilities.invokeLater(() -> {
                        if (isDisplayable()) {
                            setCursor(Cursor.getDefaultCursor());
                        }
                    });
                }
            });
        } catch (RejectedExecutionException ex) {
            ceksukses = false;
        }
    }

    @Override
    public void dispose() {
        executor.shutdownNow();
        super.dispose();
    }
}
