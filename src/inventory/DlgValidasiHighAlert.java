/*
  Dilarang keras menggandakan/mengcopy/menyebarkan/membajak/mendecompile 
  Software ini dalam bentuk apapun tanpa seijin pembuat software
  (Khanza.Soft Media). Bagi yang sengaja membajak softaware ini ta
  npa ijin, kami sumpahi sial 1000 turunan, miskin sampai 500 turu
  nan. Selalu mendapat kecelakaan sampai 400 turunan. Anak pertama
  nya cacat tidak punya kaki sampai 300 turunan. Susah cari jodoh
  sampai umur 50 tahun sampai 200 turunan. Ya Alloh maafkan kami 
  karena telah berdoa buruk, semua ini kami lakukan karena kami ti
  dak pernah rela karya kami dibajak tanpa ijin.
 */
package inventory;

import fungsi.WarnaTable2;
import fungsi.akses;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import kepegawaian.DlgCariPetugas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javax.swing.AbstractCellEditor;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 * Validasi obat High Alert berdasarkan resep.
 *
 * @author dosen
 */
public final class DlgValidasiHighAlert extends javax.swing.JDialog {

    private static final String TBL_HEADER = "cek_list_obat_high_alert";
    private static final String TBL_DETAIL = "cek_list_obat_high_alert_detail";
    private static final int COL_NO = 0, COL_KODE = 1, COL_NAMA = 2, COL_HA = 3, COL_LABEL = 4;
    private static final String UTUH = "utuh", TERPAKAI = "sudah_terpakai";

    private final DefaultTableModel tabMode;
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Connection koneksi = koneksiDB.condb();
    private PreparedStatement psobat, ps2;
    private ResultSet rsobat, rs2;
    private WarnaTable2 warna = new WarnaTable2();
    private volatile boolean loading = false;
    private boolean sukses = true;
    private int i = 0, no = 0, jumlah = 0;
    private String noresep = "", label = "", kdValidasiHeader = "";
    private DlgCariPetugas petugas;
    private Map<String, String> tersimpan = new HashMap<>();
    private String kdValidasiTersimpan = "";
    private boolean sudahAda = false, modeEdit = true;
    private boolean obatDiberikanBerhasilDimuat = false, detailValidasiBerhasilDimuat = false;

    /**
     * Creates new form DlgValidasiHighAlert
     *
     * @param parent
     * @param modal
     */
    public DlgValidasiHighAlert(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(10, 2);
        setSizeWithinParent(parent, 656, 280);

        tabMode = new DefaultTableModel(null, new Object[]{
            "No", "Kode Barang", "Nama Barang", "Obat High Alert", "Label Obat"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (!modeEdit) {
                    return false;
                }
                if (colIndex == COL_HA) {
                    a = true;
                } else if (colIndex == COL_LABEL) {
                    a = Boolean.TRUE.equals(getValueAt(rowIndex, COL_HA));
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Boolean.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        aturPetugas();
        tbObat.setModel(tabMode);
        tbObat.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (i = 0; i < 5; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(90);
            } else if (i == 2) {
                column.setPreferredWidth(300);
            } else if (i == 3) {
                column.setPreferredWidth(110);
            } else if (i == 4) {
                column.setPreferredWidth(200);
            }
        }
        warna.kolom = COL_HA;
        tbObat.setDefaultRenderer(Object.class, warna);
        tbObat.setRowHeight(24);
        tbObat.getColumnModel().getColumn(COL_LABEL).setCellRenderer(new LabelCell());
        tbObat.getColumnModel().getColumn(COL_LABEL).setCellEditor(new LabelCell());

        // jika high alert di-uncheck, label dikosongkan
        tabMode.addTableModelListener(e -> {
            if (!loading && e.getColumn() == COL_HA && e.getFirstRow() >= 0
                    && e.getFirstRow() < tabMode.getRowCount()
                    && !Boolean.TRUE.equals(tabMode.getValueAt(e.getFirstRow(), COL_HA))) {
                tabMode.setValueAt("", e.getFirstRow(), COL_LABEL);
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        internalFrame1 = new widget.InternalFrame();
        panelisi1 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnGanti = new widget.Button();
        BtnKeluar = new widget.Button();
        FormInput = new widget.PanelBiasa();
        jLabel5 = new widget.Label();
        TNoRw = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TPasien = new widget.TextBox();
        label12 = new widget.Label();
        TRuangan = new widget.TextBox();
        label21 = new widget.Label();
        label13 = new widget.Label();
        KdPetugas = new widget.TextBox();
        NmPetugas = new widget.TextBox();
        BtnPetugas = new widget.Button();
        TNoResep = new widget.TextBox();
        Scroll = new widget.ScrollPane();
        tbObat = new widget.Table();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Validasi Obat High Alert ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        panelisi1.setName("panelisi1"); // NOI18N
        panelisi1.setPreferredSize(new java.awt.Dimension(100, 43));
        panelisi1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 9, 9));

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
        panelisi1.add(BtnSimpan);

        BtnGanti.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnGanti.setMnemonic('G');
        BtnGanti.setText("Ganti");
        BtnGanti.setToolTipText("Alt+G");
        BtnGanti.setName("BtnGanti"); // NOI18N
        BtnGanti.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnGanti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGantiActionPerformed(evt);
            }
        });
        BtnGanti.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnGantiKeyPressed(evt);
            }
        });
        panelisi1.add(BtnGanti);

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
        panelisi1.add(BtnKeluar);

        internalFrame1.add(panelisi1, java.awt.BorderLayout.PAGE_END);

        FormInput.setBackground(new java.awt.Color(215, 225, 215));
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(100, 103));
        FormInput.setLayout(null);

        jLabel5.setText("No.Rawat :");
        jLabel5.setName("jLabel5"); // NOI18N
        jLabel5.setPreferredSize(new java.awt.Dimension(68, 23));
        FormInput.add(jLabel5);
        jLabel5.setBounds(4, 10, 68, 23);

        TNoRw.setEditable(false);
        TNoRw.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N
        TNoRw.setPreferredSize(new java.awt.Dimension(130, 23));
        FormInput.add(TNoRw);
        TNoRw.setBounds(75, 10, 130, 23);

        TNoRM.setEditable(false);
        TNoRM.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TNoRM.setHighlighter(null);
        TNoRM.setName("TNoRM"); // NOI18N
        TNoRM.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(TNoRM);
        TNoRM.setBounds(208, 10, 80, 23);

        TPasien.setEditable(false);
        TPasien.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        TPasien.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(TPasien);
        TPasien.setBounds(291, 10, 330, 23);

        label12.setText("Ruangan :");
        label12.setName("label12"); // NOI18N
        label12.setPreferredSize(new java.awt.Dimension(68, 23));
        FormInput.add(label12);
        label12.setBounds(4, 40, 68, 23);

        TRuangan.setEditable(false);
        TRuangan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TRuangan.setHighlighter(null);
        TRuangan.setName("TRuangan"); // NOI18N
        TRuangan.setPreferredSize(new java.awt.Dimension(130, 23));
        FormInput.add(TRuangan);
        TRuangan.setBounds(75, 40, 130, 23);

        label21.setText("No.Resep :");
        label21.setName("label21"); // NOI18N
        label21.setPreferredSize(new java.awt.Dimension(68, 23));
        FormInput.add(label21);
        label21.setBounds(208, 40, 68, 23);

        TNoResep.setEditable(false);
        TNoResep.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TNoResep.setHighlighter(null);
        TNoResep.setName("TNoResep"); // NOI18N
        TNoResep.setPreferredSize(new java.awt.Dimension(150, 23));
        FormInput.add(TNoResep);
        TNoResep.setBounds(279, 40, 150, 23);

        label13.setText("Petugas :");
        label13.setName("label13"); // NOI18N
        label13.setPreferredSize(new java.awt.Dimension(68, 23));
        FormInput.add(label13);
        label13.setBounds(4, 70, 68, 23);

        KdPetugas.setEditable(false);
        KdPetugas.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        KdPetugas.setHighlighter(null);
        KdPetugas.setName("KdPetugas"); // NOI18N
        KdPetugas.setPreferredSize(new java.awt.Dimension(100, 23));
        FormInput.add(KdPetugas);
        KdPetugas.setBounds(75, 70, 100, 23);

        NmPetugas.setEditable(false);
        NmPetugas.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        NmPetugas.setHighlighter(null);
        NmPetugas.setName("NmPetugas"); // NOI18N
        NmPetugas.setPreferredSize(new java.awt.Dimension(380, 23));
        FormInput.add(NmPetugas);
        NmPetugas.setBounds(178, 70, 380, 23);

        BtnPetugas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPetugas.setMnemonic('2');
        BtnPetugas.setToolTipText("ALt+2");
        BtnPetugas.setName("BtnPetugas"); // NOI18N
        BtnPetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPetugasActionPerformed(evt);
            }
        });
        BtnPetugas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPetugasKeyPressed(evt);
            }
        });
        FormInput.add(BtnPetugas);
        BtnPetugas.setBounds(561, 70, 28, 23);

        internalFrame1.add(FormInput, java.awt.BorderLayout.PAGE_START);

        Scroll.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbObat.setToolTipText("Centang obat high alert, lalu pilih label obat");
        tbObat.setName("tbObat"); // NOI18N
        Scroll.setViewportView(tbObat);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>                        

    private void setSizeWithinParent(java.awt.Frame parent, int width, int height) {
        java.awt.Rectangle screenBounds = java.awt.GraphicsEnvironment
                .getLocalGraphicsEnvironment().getMaximumWindowBounds();
        int maxWidth = screenBounds.width;
        int maxHeight = screenBounds.height;
        if (parent != null && parent.getWidth() > 0 && parent.getHeight() > 0) {
            maxWidth = parent.getWidth();
            maxHeight = parent.getHeight();
        }
        setSize(Math.min(width, maxWidth), Math.min(height, maxHeight));
    }

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {
        simpan();
    }

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            simpan();
        } else {
            Valid.pindah(evt, BtnPetugas, BtnGanti);
        }
    }

    private void BtnGantiActionPerformed(java.awt.event.ActionEvent evt) {
        ganti();
    }

    private void BtnGantiKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnGantiActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnSimpan, BtnKeluar);
        }
    }

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
    }

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnGanti, TNoResep);
        }
    }

    private void BtnPetugasActionPerformed(java.awt.event.ActionEvent evt) {
        if (petugas == null || !petugas.isDisplayable()) {
            petugas = new DlgCariPetugas(null, false);
            // form ini modal; tanpa ini dialog cari petugas ikut terblokir (tidak bisa diklik / tertutup di belakang)
            petugas.setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
            petugas.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            petugas.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    if (petugas.getTable().getSelectedRow() != -1) {
                        KdPetugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString());
                        NmPetugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                    }
                    BtnPetugas.requestFocus();
                    petugas = null;
                }
            });

            petugas.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
            petugas.setLocationRelativeTo(internalFrame1);
        }
        if (petugas == null) {
            return;
        }
        if (!petugas.isVisible()) {
            petugas.isCek();
            petugas.emptTeks();
        }

        if (petugas.isVisible()) {
            petugas.toFront();
            return;
        }
        petugas.setVisible(true);
        petugas.toFront();
    }

    private void BtnPetugasKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPetugasActionPerformed(null);
        } else {
            Valid.pindah(evt, TNoResep, BtnSimpan);
        }
    }

    /**
     * Jika bukan Admin Utama, petugas otomatis diambil dari session login dan
     * tidak bisa diganti.
     */
    private void aturPetugas() {
        if (akses.getkode().equals("Admin Utama")) {
            BtnPetugas.setEnabled(true);
            KdPetugas.setText("");
            NmPetugas.setText("");
        } else {
            BtnPetugas.setEnabled(false);
            KdPetugas.setText(akses.getkode());
            NmPetugas.setText(Sequel.cariIsi("select petugas.nama from petugas where petugas.nip=?", akses.getkode()));
        }
    }

    /**
     * Resep yang sudah divalidasi tetap dapat diedit agar detailnya bisa
     * disinkronkan dengan perubahan checkbox.
     */
    private void aturMode() {
        BtnSimpan.setEnabled(true);
        BtnGanti.setEnabled(true);
        BtnPetugas.setEnabled(modeEdit && akses.getkode().equals("Admin Utama"));
        tbObat.repaint();
    }

    // Variables declaration - do not modify                     
    private widget.Button BtnGanti;
    private widget.Button BtnKeluar;
    private widget.Button BtnPetugas;
    private widget.Button BtnSimpan;
    private widget.PanelBiasa FormInput;
    private widget.ScrollPane Scroll;
    private widget.TextBox KdPetugas;
    private widget.TextBox NmPetugas;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox TRuangan;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel5;
    private widget.Label label12;
    private widget.Label label13;
    private widget.Label label21;
    private widget.panelisi panelisi1;
    private widget.TextBox TNoResep;
    private widget.Table tbObat;
    // End of variables declaration                   

    /**
     * Dipanggil sebelum setVisible(true). Resep terbaru diambil dari no_rawat.
     */
    public void setData(String noRawat, String noRM, String pasien,
            String namaBangsal, String kodeKamar) {

        TNoRw.setText(noRawat);
        TNoRM.setText(noRM);
        TPasien.setText(pasien);

        // Menampilkan nama bangsal dan kode kamar
        if (namaBangsal != null && !namaBangsal.isEmpty()) {
            if (kodeKamar != null && !kodeKamar.isEmpty()) {
                TRuangan.setText(namaBangsal + " / " + kodeKamar);
            } else {
                TRuangan.setText(namaBangsal);
            }
        } else {
            TRuangan.setText("Ruang tidak ditemukan");
        }

        aturPetugas();
        loadResep(null);
    }

    public void setData(String noRawat, String noRM, String pasien,
            String namaBangsal, String kodeKamar, String noResep) {

        TNoRw.setText(noRawat);
        TNoRM.setText(noRM);
        TPasien.setText(pasien);

        if (namaBangsal != null && !namaBangsal.isEmpty()) {
            if (kodeKamar != null && !kodeKamar.isEmpty()) {
                TRuangan.setText(namaBangsal + " / " + kodeKamar);
            } else {
                TRuangan.setText(namaBangsal);
            }
        } else {
            TRuangan.setText("Ruang tidak ditemukan");
        }

        aturPetugas();
        // jika no_resep dikirim, pakai itu; jika kosong, baru ambil resep terbaru
        loadResep((noResep == null || noResep.trim().isEmpty()) ? null : noResep.trim());
    }

    /**
     * Alternatif: langsung pilih satu resep tertentu.
     */
    public void setNoResep(String noResep) {
        loadResep(noResep);
    }

    public void emptTeks() {
        BtnSimpan.requestFocus();
    }

    public JTable getTable() {
        return tbObat;
    }

    private void loadResep(String pilihan) {
        loading = true;
        String resep = pilihan;
        TNoResep.setText("");
        if (resep == null) {
            try {
                psobat = koneksi.prepareStatement(
                        "select resep_obat.no_resep from resep_obat "
                        + "where resep_obat.no_rawat=? "
                        + "order by resep_obat.no_resep desc limit 1");
                try {
                    psobat.setString(1, TNoRw.getText());
                    rsobat = psobat.executeQuery();
                    if (rsobat.next()) {
                        resep = rsobat.getString(1);
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                } finally {
                    if (rsobat != null) {
                        rsobat.close();
                    }
                    if (psobat != null) {
                        psobat.close();
                    }
                }
            } catch (Exception e) {
                System.out.println("Gagal menyiapkan daftar obat yang sudah diberikan: " + e);
                JOptionPane.showMessageDialog(rootPane,
                        "Gagal menyiapkan daftar obat yang sudah diberikan:\n" + e.getMessage());
            }
        }

        TNoResep.setText(resep == null ? "" : resep);
        loading = false;
        tampil();
    }

    /**
     * Tampilkan obat yang tercatat diberikan untuk resep terpilih, isi ulang
     * dari data tersimpan bila ada.
     */
    public void tampil() {
        loading = true;
        Valid.tabelKosong(tabMode);
        obatDiberikanBerhasilDimuat = false;
        detailValidasiBerhasilDimuat = false;
        noresep = TNoResep.getText().trim();
        if (noresep.isEmpty()) {
            kdValidasiTersimpan = "";
            sudahAda = false;
            tersimpan.clear();
            modeEdit = false;
            loading = false;
            aturMode();
            return;
        }

        // petugas: pakai petugas yang tersimpan bila resep ini sudah divalidasi
        aturPetugas();
        kdValidasiTersimpan = Sequel.cariIsi("select " + TBL_HEADER + ".kd_validasi from " + TBL_HEADER
                + " where " + TBL_HEADER + ".no_resep=? order by " + TBL_HEADER + ".kd_validasi desc limit 1", noresep);
        sudahAda = !kdValidasiTersimpan.equals("");
        modeEdit = true;
        String nipTersimpan = Sequel.cariIsi("select " + TBL_HEADER + ".nip_petugas1 from " + TBL_HEADER
                + " where " + TBL_HEADER + ".no_resep=? order by " + TBL_HEADER + ".kd_validasi desc limit 1", noresep);
        if (!nipTersimpan.equals("")) {
            KdPetugas.setText(nipTersimpan);
            NmPetugas.setText(Sequel.cariIsi("select petugas.nama from petugas where petugas.nip=?", nipTersimpan));
        }

        tersimpan = new HashMap<>();
        try {
            ps2 = koneksi.prepareStatement(
                    "select " + TBL_DETAIL + ".kd_brng," + TBL_DETAIL + ".label_obat from " + TBL_DETAIL
                    + " where " + TBL_DETAIL + ".kd_validasi=?");
            try {
                ps2.setString(1, kdValidasiTersimpan);
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    tersimpan.put(rs2.getString(1), rs2.getString(2));
                }
                detailValidasiBerhasilDimuat = true;
            } catch (Exception e) {
                System.out.println("Gagal mengambil detail validasi High Alert: " + e);
                JOptionPane.showMessageDialog(rootPane,
                        "Gagal mengambil detail validasi High Alert:\n" + e.getMessage());
            } finally {
                if (rs2 != null) {
                    rs2.close();
                }
                if (ps2 != null) {
                    ps2.close();
                }
            }

            psobat = koneksi.prepareStatement(
                    "select distinct detail_pemberian_obat.kode_brng,databarang.nama_brng "
                    + "from resep_obat "
                    + "inner join detail_pemberian_obat on detail_pemberian_obat.no_rawat=resep_obat.no_rawat "
                    + "and detail_pemberian_obat.tgl_perawatan=resep_obat.tgl_perawatan "
                    + "and detail_pemberian_obat.jam=resep_obat.jam "
                    + "inner join databarang on databarang.kode_brng=detail_pemberian_obat.kode_brng "
                    + "where resep_obat.no_resep=? order by databarang.nama_brng");
            try {
                psobat.setString(1, noresep);
                rsobat = psobat.executeQuery();
                no = 1;
                while (rsobat.next()) {
                    label = tersimpan.get(rsobat.getString(1));
                    tabMode.addRow(new Object[]{
                        no, rsobat.getString(1), rsobat.getString(2),
                        label != null, label != null ? label : ""
                    });
                    no++;
                }
                obatDiberikanBerhasilDimuat = true;
            } catch (Exception e) {
                System.out.println("Gagal mengambil daftar obat yang sudah diberikan: " + e);
                JOptionPane.showMessageDialog(rootPane,
                        "Gagal mengambil daftar obat yang sudah diberikan:\n" + e.getMessage());
            } finally {
                if (rsobat != null) {
                    rsobat.close();
                }
                if (psobat != null) {
                    psobat.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        loading = false;
        aturMode();
    }

    private void simpan() {
        prosesSimpan(false);
    }

    private void ganti() {
        prosesSimpan(true);
    }

    private void prosesSimpan(boolean hanyaGanti) {
        if (tbObat.isEditing()) {
            tbObat.getCellEditor().stopCellEditing();
        }
        noresep = TNoResep.getText().trim();
        if (noresep.isEmpty()) {
            Valid.textKosong(TNoResep, "No.Resep");
            return;
        }
        if (!obatDiberikanBerhasilDimuat || !detailValidasiBerhasilDimuat) {
            JOptionPane.showMessageDialog(rootPane,
                    "Data obat yang diberikan atau detail validasi belum berhasil dimuat. Silakan muat ulang resep sebelum menyimpan..!!");
            return;
        }
        if (hanyaGanti && !sudahAda) {
            JOptionPane.showMessageDialog(rootPane, "Data validasi resep ini belum tersimpan. Gunakan tombol Simpan..!!");
            return;
        }
        if (!akses.getkode().equals("Admin Utama")) {
            aturPetugas();
        }
        if (KdPetugas.getText().trim().equals("") || NmPetugas.getText().trim().equals("")) {
            Valid.textKosong(BtnPetugas, "Petugas Validasi");
            return;
        }

        jumlah = 0;
        for (i = 0; i < tbObat.getRowCount(); i++) {
            if (Boolean.TRUE.equals(tbObat.getValueAt(i, COL_HA))) {
                label = String.valueOf(tbObat.getValueAt(i, COL_LABEL));
                if (!label.equals(UTUH) && !label.equals(TERPAKAI)) {
                    JOptionPane.showMessageDialog(rootPane, "Label obat (Utuh / Sudah Terpakai) untuk " + tbObat.getValueAt(i, COL_NAMA) + " belum dipilih..!!");
                    tbObat.setRowSelectionInterval(i, i);
                    return;
                }
                jumlah++;
            }
        }
        if (jumlah == 0) {
            if (sudahAda) {
                hapusValidasi();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Belum ada obat high alert yang dicentang..!!");
            }
            return;
        }

        int reply = JOptionPane.showConfirmDialog(rootPane, "Eeiiiiiits, udah bener belum data yang mau disimpan..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            try {
                Sequel.AutoComitFalse();
                sukses = true;
                if (sudahAda) {
                    gantiDetail();
                } else {
                    simpanBaru();
                }
                selesaiTransaksi("Validasi obat high alert berhasil disimpan..!!");
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }

    /**
     * Resep ini belum pernah divalidasi: simpan header + detail baru.
     */
    private void simpanBaru() {
        kdValidasiHeader = buatKodeValidasi();
        if (kdValidasiHeader.isEmpty()) {
            sukses = false;
            return;
        }
        if (Sequel.queryu2tf("insert into " + TBL_HEADER
                + "(kd_validasi,no_rkm_medis,ruangan,no_resep,nip_petugas1,validasi_petugas1) values(?,?,?,?,?,1)", 5, new String[]{
                    kdValidasiHeader, TNoRM.getText(), TRuangan.getText(), noresep, KdPetugas.getText()
                }) == false) {
            sukses = false;
        }

        if (sukses == true) {
            for (i = 0; i < tbObat.getRowCount(); i++) {
                if (Boolean.TRUE.equals(tbObat.getValueAt(i, COL_HA))) {
                    if (Sequel.queryu2tf("insert into " + TBL_DETAIL + "(kd_validasi,kd_brng,label_obat) values(?,?,?)", 3, new String[]{
                        kdValidasiHeader, tbObat.getValueAt(i, COL_KODE).toString(), tbObat.getValueAt(i, COL_LABEL).toString()
                    }) == false) {
                        sukses = false;
                    }
                }
            }
        }
    }

    private String buatKodeValidasi() {
        String tanggal = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String prefix = "VHA" + tanggal;
        String nomorTerakhir = Sequel.cariIsi("select ifnull(MAX(CONVERT(RIGHT(kd_validasi,3),signed)),0) from "
                + TBL_HEADER + " where kd_validasi like '" + prefix + "%'");
        if (nomorTerakhir == null || nomorTerakhir.trim().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Gagal mengambil nomor urut validasi high alert..!!");
            return "";
        }

        int nomorBerikutnya;
        try {
            nomorBerikutnya = Integer.parseInt(nomorTerakhir.trim()) + 1;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(rootPane, "Nomor urut validasi high alert tidak valid..!!");
            return "";
        }
        if (nomorBerikutnya > 999) {
            JOptionPane.showMessageDialog(rootPane, "Nomor urut validasi high alert hari ini sudah mencapai batas 999..!!");
            return "";
        }
        return prefix + String.format(Locale.ROOT, "%03d", nomorBerikutnya);
    }

    /**
     * Resep ini sudah divalidasi: header dipertahankan, detail disinkronkan
     * dengan tabel. Dicentang & belum ada = tambah, label berubah = update,
     * di-uncheck & sudah ada = hapus.
     */
    private void gantiDetail() {
        kdValidasiHeader = kdValidasiTersimpan;
        if (Sequel.queryu2tf("update " + TBL_HEADER + " set ruangan=?,nip_petugas1=? where kd_validasi=?", 3, new String[]{
            TRuangan.getText(), KdPetugas.getText(), kdValidasiHeader
        }) == false) {
            sukses = false;
        }

        Set<String> kodeTabel = new HashSet<>();
        for (int r = 0; r < tbObat.getRowCount() && sukses == true; r++) {
            String kode = tbObat.getValueAt(r, COL_KODE).toString();
            String lama = tersimpan.get(kode);
            kodeTabel.add(kode);
            if (!Boolean.TRUE.equals(tbObat.getValueAt(r, COL_HA)) && lama != null) {
                if (Sequel.queryu2tf("delete from " + TBL_DETAIL + " where kd_validasi=? and kd_brng=?", 2, new String[]{
                    kdValidasiHeader, kode
                }) == false) {
                    sukses = false;
                }
            }
        }

        for (String kode : tersimpan.keySet()) {
            if (sukses == true && !kodeTabel.contains(kode)) {
                if (Sequel.queryu2tf("delete from " + TBL_DETAIL + " where kd_validasi=? and kd_brng=?", 2, new String[]{
                    kdValidasiHeader, kode
                }) == false) {
                    sukses = false;
                }
            }
        }

        for (int r = 0; r < tbObat.getRowCount() && sukses == true; r++) {
            if (Boolean.TRUE.equals(tbObat.getValueAt(r, COL_HA))) {
                String kode = tbObat.getValueAt(r, COL_KODE).toString();
                String lama = tersimpan.get(kode);
                String baru = tbObat.getValueAt(r, COL_LABEL).toString();
                if (lama == null) {
                    if (Sequel.queryu2tf("insert into " + TBL_DETAIL + "(kd_validasi,kd_brng,label_obat) values(?,?,?)", 3, new String[]{
                        kdValidasiHeader, kode, baru
                    }) == false) {
                        sukses = false;
                    }
                } else if (!lama.equals(baru)) {
                    if (Sequel.queryu2tf("update " + TBL_DETAIL + " set label_obat=? where kd_validasi=? and kd_brng=?", 3, new String[]{
                        baru, kdValidasiHeader, kode
                    }) == false) {
                        sukses = false;
                    }
                }
            }
        }
    }

    /**
     * Semua obat di-uncheck pada resep yang sudah divalidasi: hapus validasi.
     */
    private void hapusValidasi() {
        int reply = JOptionPane.showConfirmDialog(rootPane, "Tidak ada obat high alert yang dicentang.\nHapus data validasi high alert resep ini..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            try {
                Sequel.AutoComitFalse();
                sukses = true;
                if (Sequel.queryu2tf("delete from " + TBL_DETAIL + " where kd_validasi=?", 1, new String[]{
                    kdValidasiTersimpan
                }) == false) {
                    sukses = false;
                }
                if (Sequel.queryu2tf("delete from " + TBL_HEADER + " where kd_validasi=?", 1, new String[]{
                    kdValidasiTersimpan
                }) == false) {
                    sukses = false;
                }
                selesaiTransaksi("Data validasi high alert berhasil dihapus..!!");
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }

    private void selesaiTransaksi(String pesanSukses) {
        if (sukses == true) {
            Sequel.Commit();
        } else {
            sukses = false;
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat pemrosesan data, transaksi dibatalkan.\nPeriksa kembali data sebelum melanjutkan menyimpan..!!");
            Sequel.RollBack();
        }

        Sequel.AutoComitTrue();

        if (sukses == true) {
            JOptionPane.showMessageDialog(rootPane, pesanSukses);
            dispose();
        }
    }

    /**
     * Sel berisi 2 radio button (Utuh / Sudah Terpakai); dipakai sebagai
     * renderer dan editor.
     */
    private final class LabelCell extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {

        private final JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
        private final JRadioButton rbUtuh = new JRadioButton("Utuh");
        private final JRadioButton rbTerpakai = new JRadioButton("Sudah Terpakai");
        private final ButtonGroup group = new ButtonGroup();
        private String nilai = "";

        LabelCell() {
            group.add(rbUtuh);
            group.add(rbTerpakai);
            rbUtuh.setOpaque(false);
            rbTerpakai.setOpaque(false);
            rbUtuh.setFont(new java.awt.Font("Tahoma", 0, 11));
            rbTerpakai.setFont(new java.awt.Font("Tahoma", 0, 11));
            panel.add(rbUtuh);
            panel.add(rbTerpakai);
            rbUtuh.addActionListener(e -> {
                nilai = UTUH;
                stopCellEditing();
            });
            rbTerpakai.addActionListener(e -> {
                nilai = TERPAKAI;
                stopCellEditing();
            });
        }

        private Component siapkan(JTable table, Object value, boolean selected, int row) {
            nilai = value == null ? "" : value.toString();
            group.clearSelection();
            if (UTUH.equals(nilai)) {
                rbUtuh.setSelected(true);
            } else if (TERPAKAI.equals(nilai)) {
                rbTerpakai.setSelected(true);
            }
            boolean aktif = modeEdit && Boolean.TRUE.equals(table.getValueAt(row, COL_HA));
            rbUtuh.setEnabled(aktif);
            rbTerpakai.setEnabled(aktif);
            if (selected) {
                panel.setBackground(table.getSelectionBackground());
            } else if (row % 2 == 1) {
                panel.setBackground(new Color(255, 255, 255));
            } else {
                panel.setBackground(new Color(250, 255, 245));
            }
            return panel;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {
            return siapkan(table, value, isSelected, row);
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected,
                int row, int column) {
            return siapkan(table, value, true, row);
        }

        @Override
        public Object getCellEditorValue() {
            return nilai;
        }
    }
}
