package model;

import java.util.ArrayList;
import java.util.List;

public class ReferralRepository {
    private final List<Referral> referrals = new ArrayList<>();

    public List<Referral> getAll() { return referrals; }
    public int size() { return referrals.size(); }
    public void add(Referral r) { referrals.add(r); }
}
