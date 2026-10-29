package rekammedis;

import fungsi.koneksiDB;
import fungsi.sekuel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Lapisan transparan di atas gambar Status Lokalis. Klik kiri = tambah titik,
 * klik kanan = hapus titik terakhir. Koordinat disimpan sebagai rasio 0..1
 * terhadap lebar/tinggi panel, jadi tetap pas walau ukuran panel berubah.
 * Dipakai bersama oleh form medis IGD dan medis ranap umum.
 */
public class PanelLokalis extends JPanel {

    private final Connection koneksi = koneksiDB.condb();
    private final sekuel Sequel = new sekuel();
    private final List<double[]> titik = new ArrayList<>();
    private final List<String> ket = new ArrayList<>();
    private Runnable onUbah;

    public void setOnUbah(Runnable r) {
        this.onUbah = r;
    }

    /** Hasil: "1. luka robek 3 cm\n2. memar" (hanya titik yang ada keterangannya) */
    public String teksKeterangan() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ket.size(); i++) {
            if (!ket.get(i).trim().isEmpty()) {
                sb.append(i + 1).append(". ").append(ket.get(i).trim()).append("\n");
            }
        }
        return sb.toString().trim();
    }
    public PanelLokalis() {
        setOpaque(false);
        setLayout(null);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {   // bukan mouseClicked
                System.out.println("Lokalis klik di " + e.getX() + "," + e.getY()
                        + " ukuran panel " + getWidth() + "x" + getHeight());
                if (getWidth() <= 0 || getHeight() <= 0) {
                    return;
                }
                if (SwingUtilities.isRightMouseButton(e)) {
                    if (!titik.isEmpty()) {
                        titik.remove(titik.size() - 1);
                        ket.remove(ket.size() - 1);
                    }
                } else {
                    String k = JOptionPane.showInputDialog(PanelLokalis.this, "Keterangan (boleh kosong):");
                    if (k == null) {
                        return;
                    }
                    titik.add(new double[]{(double) e.getX() / getWidth(), (double) e.getY() / getHeight()});
                    ket.add(k.length() > 100 ? k.substring(0, 100) : k);
                }
                repaint();
                if (onUbah != null) {
                    onUbah.run();
                }
            }
        });
    }

    public void kosongkan() {
        titik.clear();
        ket.clear();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        gambarTitik((Graphics2D) g, getWidth(), getHeight(), titik, Math.max(18, getHeight() / 11), 12);
    }

    /**
     * Menandai tiap titik dengan panah: ujung panah tepat pada koordinat, pangkal
     * panah ke arah luar (menjauhi tengah panel) dan diberi nomor urut.
     */
    private static void gambarTitik(Graphics2D g, int w, int h, List<double[]> pts, int panjang, int fontSize) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setFont(g.getFont().deriveFont(Font.BOLD, (float) fontSize));
        FontMetrics fm = g.getFontMetrics();
        g.setColor(Color.RED);
        int n = 1;
        for (double[] p : pts) {
            int px = (int) Math.round(p[0] * w), py = (int) Math.round(p[1] * h);
            double dx = px - w / 2.0, dy = py - h / 2.0;
            double d = Math.hypot(dx, dy);
            if (d < 1) {                     // titik tepat di tengah: arah panah ke kanan atas
                dx = 1;
                dy = -1;
                d = Math.sqrt(2);
            }
            dx /= d;
            dy /= d;
            double ruang = ruangKosong(px, py, dx, dy, w, h);
            if (ruang < Math.min(panjang, 14)) {   // terlalu mepet tepi: panah dari arah dalam panel
                double balik = ruangKosong(px, py, -dx, -dy, w, h);
                if (balik > ruang) {
                    dx = -dx;
                    dy = -dy;
                    ruang = balik;
                }
            }
            double jarak = Math.min(panjang, ruang);
            int tailX = (int) Math.round(px + dx * jarak), tailY = (int) Math.round(py + dy * jarak);
            gambarPanah(g, px, py, tailX, tailY, Math.max(1.5f, panjang / 12f));

            String nomor = String.valueOf(n++);
            int tw = fm.stringWidth(nomor), xTeks, yTeks;
            if (dx > 0.3) {
                xTeks = tailX + 3;
            } else if (dx < -0.3) {
                xTeks = tailX - 3 - tw;
            } else {
                xTeks = tailX - tw / 2;
            }
            if (dy > 0.3) {
                yTeks = tailY + fm.getHeight() - 3;
            } else if (dy < -0.3) {
                yTeks = tailY - 4;
            } else {
                yTeks = tailY + (fm.getAscent() - fm.getDescent()) / 2;
            }
            g.drawString(nomor, Math.max(1, Math.min(xTeks, w - tw - 1)),
                    Math.max(fm.getAscent(), Math.min(yTeks, h - 1)));
        }
    }

    /** Jarak maksimum pangkal panah dari titik agar masih berada di dalam panel. */
    private static double ruangKosong(int px, int py, double dx, double dy, int w, int h) {
        double t = Double.MAX_VALUE;
        if (dx > 0) t = Math.min(t, (w - 4 - px) / dx);
        if (dx < 0) t = Math.min(t, (4 - px) / dx);
        if (dy > 0) t = Math.min(t, (h - 4 - py) / dy);
        if (dy < 0) t = Math.min(t, (4 - py) / dy);
        return Math.max(0, t);
    }

    /** Garis panah dari pangkal (tail) ke ujung (tip) beserta kepala panah di ujungnya. */
    private static void gambarPanah(Graphics2D g, int tipX, int tipY, int tailX, int tailY, float tebal) {
        double sudut = Math.atan2(tailY - tipY, tailX - tipX);
        double jarak = Math.hypot(tailX - tipX, tailY - tipY);
        double kepala = Math.min(10, Math.max(4, jarak / 2.5));
        int dasarX = (int) Math.round(tipX + Math.cos(sudut) * kepala);
        int dasarY = (int) Math.round(tipY + Math.sin(sudut) * kepala);
        g.setStroke(new BasicStroke(tebal, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        if (jarak > kepala) {
            g.drawLine(tailX, tailY, dasarX, dasarY);
        }
        double lebar = kepala * 0.5;
        g.fillPolygon(new int[]{tipX,
            (int) Math.round(dasarX + Math.cos(sudut + Math.PI / 2) * lebar),
            (int) Math.round(dasarX + Math.cos(sudut - Math.PI / 2) * lebar)},
                new int[]{tipY,
                    (int) Math.round(dasarY + Math.sin(sudut + Math.PI / 2) * lebar),
                    (int) Math.round(dasarY + Math.sin(sudut - Math.PI / 2) * lebar)}, 3);
    }

    /** Baca titik dari tabel anak (tabel hanya dari kode, bukan input pengguna). */
    private static void baca(Connection koneksi, String tabel, String noRawat, List<double[]> pts, List<String> kts) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = koneksi.prepareStatement("select x,y,keterangan from " + tabel + " where no_rawat=? order by no_urut");
            ps.setString(1, noRawat);
            rs = ps.executeQuery();
            while (rs.next()) {
                pts.add(new double[]{rs.getDouble(1), rs.getDouble(2)});
                kts.add(rs.getString(3) == null ? "" : rs.getString(3));
            }
        } catch (Exception e) {
            System.out.println("Notif Lokalis : " + e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (Exception e) {
            }
        }
    }

    public void muat(String tabel, String noRawat) {
        titik.clear();
        ket.clear();
        baca(koneksi, tabel, noRawat, titik, ket);
        repaint();
    }

    /** Hapus lalu insert ulang semua titik untuk no_rawat tsb. */
    public void simpan(String tabel, String noRawat) {
        Sequel.queryu2tf("delete from " + tabel + " where no_rawat=?", 1, new String[]{noRawat});
        for (int i = 0; i < titik.size(); i++) {
            Sequel.menyimpantf(tabel, "?,?,?,?,?", "Titik lokalis", 5, new String[]{
                noRawat, String.valueOf(i + 1),
                String.valueOf(titik.get(i)[0]), String.valueOf(titik.get(i)[1]), ket.get(i)
            });
        }
    }

    /**
     * Untuk cetak Jasper: gambar asli + titik dari database, dikembalikan
     * sebagai InputStream PNG sehingga parameter "lokalis" di .jasper tidak
     * perlu diubah (tetap java.io.InputStream).
     */
    public static InputStream gambarCetak(String tabel, String noRawat, String resourceGambar) {
        try {
            BufferedImage img = ImageIO.read(PanelLokalis.class.getResource(resourceGambar));
            BufferedImage out = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = out.createGraphics();
            g.drawImage(img, 0, 0, null);
            List<double[]> pts = new ArrayList<>();
            List<String> kts = new ArrayList<>();
            baca(koneksiDB.condb(), tabel, noRawat, pts, kts);
            gambarTitik(g, img.getWidth(), img.getHeight(), pts,
                    Math.max(18, img.getWidth() / 22), Math.max(11, img.getWidth() / 50));
            g.dispose();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(out, "png", bos);
            return new ByteArrayInputStream(bos.toByteArray());
        } catch (Exception e) {
            System.out.println("Notif Cetak Lokalis : " + e);
            return null;
        }
    }
}
