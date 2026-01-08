package view.tables;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import model.Referral;

public class ReferralTableModel extends AbstractTableModel {

    private final String[] columns = {"Referral ID", "Patient ID", "Urgency", "Status", "Date"};
    private final List<Referral> referrals;

    public ReferralTableModel(List<Referral> referrals) {
        this.referrals = referrals;
    }
    public void refresh() {
        fireTableDataChanged();
    }

    @Override public int getRowCount() { return referrals.size(); }
    @Override public int getColumnCount() { return columns.length; }
    @Override public String getColumnName(int c) { return columns[c]; }

    @Override
    public Object getValueAt(int row, int col) {
        Referral r = referrals.get(row);
        return switch (col) {
            case 0 -> r.getReferralId();
            case 1 -> r.getPatientId();
            case 2 -> r.getUrgencyLevel();
            case 3 -> r.getStatus();
            case 4 -> r.getReferralDate();
            default -> "";
        };
    }
}
