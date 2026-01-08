package model;

import java.util.ArrayList;
import java.util.List;

public class ReferralRepository {
    private final List<Referral> referrals = new ArrayList<>();

    public List<Referral> getAll() { return referrals; }
    public int size() { return referrals.size(); }

    public void add(Referral r) { referrals.add(r); }

    public Referral findById(String id) {
        for (Referral r : referrals) {
            if (r.getReferralId().equals(id)) return r;
        }
        return null;
    }

    public boolean removeById(String id) {
        Referral r = findById(id);
        if (r == null) return false;
        return referrals.remove(r);
    }
}
