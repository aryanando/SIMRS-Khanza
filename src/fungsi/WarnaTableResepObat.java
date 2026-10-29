package fungsi;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class WarnaTableResepObat extends DefaultTableCellRenderer {

    private final sekuel Sequel = new sekuel();

    @Override
    public Component getTableCellRendererComponent(
            JTable table,
            Object value,
            boolean isSelected,
            boolean hasFocus,
            int row,
            int column) {

        Component component = super.getTableCellRendererComponent(
                table, value, isSelected, hasFocus, row, column);

        // Warna default selang-seling
        if (row % 2 == 1) {

            component.setBackground(new Color(255, 244, 244));
            component.setForeground(new Color(50, 50, 50));
        } else {
            component.setBackground(new Color(255, 255, 255));
            component.setForeground(new Color(50, 50, 50));
        }

        try {
            // No. Resep berada di index 0
            Object objNoResep = table.getValueAt(row, 0);

            if (objNoResep != null) {
                String noResep = objNoResep.toString().trim();

                if (!noResep.equals("")) {

                    int cyto = Sequel.cariInteger(
                            "select count(*) from resep_cyto "
                            + "where no_resep='" + noResep + "'"
                    );

                    // Jika resep adalah resep CYTO
                    if (cyto > 0) {
//                        component.setBackground(new Color(255, 120, 120));
//                        component.setForeground(Color.BLACK);
                        component.setBackground(new Color(255, 100, 100));
                        component.setForeground(Color.BLACK);
                    }
                }
            }

        } catch (Exception e) {
            // Jika terjadi error, gunakan warna default
        }

        return component;
    }
}
