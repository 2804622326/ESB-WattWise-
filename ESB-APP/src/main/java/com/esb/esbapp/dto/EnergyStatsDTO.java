package com.esb.esbapp.dto;

public class EnergyStatsDTO {
    private double used;
    private int earned;

    public EnergyStatsDTO() {
    }

    public EnergyStatsDTO(double used, int earned) {
        this.used = used;
        this.earned = earned;
    }

    public double getUsed() {
        return used;
    }

    public void setUsed(double used) {
        this.used = used;
    }

    public int getEarned() {
        return earned;
    }

    public void setEarned(int earned) {
        this.earned = earned;
    }
}
