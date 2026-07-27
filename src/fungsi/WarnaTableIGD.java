package fungsi;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class WarnaTableIGD extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Warna default selang-seling
        if (row % 2 == 1) {
            component.setBackground(new Color(255, 244, 244));
            component.setForeground(new Color(50, 50, 50));
        } else {
            component.setBackground(new Color(255, 255, 255));
            component.setForeground(new Color(50, 50, 50));
        }

// Kolom "Umur" ada di index 10 sesuai struktur tbPetugas
        try {
            String umurStr = table.getValueAt(row, 10).toString().trim();
            int umur = parseUmur(umurStr);

            if (umur >= 60) {
                component.setBackground(new Color(205, 133, 63)); // Peru
                component.setForeground(Color.BLACK);
            }
        } catch (Exception e) {
            // biarkan warna default kalau gagal parsing
        }

        return component;
    }

    // Ambil angka tahun pertama dari string umur, misal "65 Th 3 Bl 2 Hr" -> 65
    private int parseUmur(String umurStr) {
        StringBuilder angka = new StringBuilder();
        for (char c : umurStr.toCharArray()) {
            if (Character.isDigit(c)) {
                angka.append(c);
            } else {
                break;
            }
        }
        return angka.length() > 0 ? Integer.parseInt(angka.toString()) : 0;
    }
}
