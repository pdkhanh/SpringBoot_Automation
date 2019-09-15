package automation.test.data;

public class Card {
    private String insurer;
    private String planname;
    private int policyPrice;
    private Float scoreResult;
    private int personalAcident;
    private int medicalExpenses;
    private int tripCancellation;
    private int tripTerminaltion;
    private int lossOfBaggageAndPersonal;
    private int reviewScore;

    public String getInsurer() {
        return insurer;
    }

    public void setInsurer(String insurer) {
        this.insurer = insurer;
    }

    public String getPlanname() {
        return planname;
    }

    public void setPlanname(String planname) {
        this.planname = planname;
    }

    public int getPolicyPrice() {
        return policyPrice;
    }

    public void setPolicyPrice(int policyPrice) {
        this.policyPrice = policyPrice;
    }

    public Float getScoreResult() {
        return scoreResult;
    }

    public void setScoreResult(Float scoreResult) {
        this.scoreResult = scoreResult;
    }

    public int getPersonalAcident() {
        return personalAcident;
    }

    public void setPersonalAcident(int personalAcident) {
        this.personalAcident = personalAcident;
    }

    public int getMedicalExpenses() {
        return medicalExpenses;
    }

    public void setMedicalExpenses(int medicalExpenses) {
        this.medicalExpenses = medicalExpenses;
    }

    public int getTripCancellation() {
        return tripCancellation;
    }

    public void setTripCancellation(int tripCancellation) {
        this.tripCancellation = tripCancellation;
    }

    public int getTripTerminaltion() {
        return tripTerminaltion;
    }

    public void setTripTerminaltion(int tripTerminaltion) {
        this.tripTerminaltion = tripTerminaltion;
    }

    public int getLossOfBaggageAndPersonal() {
        return lossOfBaggageAndPersonal;
    }

    public void setLossOfBaggageAndPersonal(int lossOfBaggageAndPersonal) {
        this.lossOfBaggageAndPersonal = lossOfBaggageAndPersonal;
    }

    public int getReviewScore() {
        return reviewScore;
    }

    public void setReviewScore(int reviewScore) {
        this.reviewScore = reviewScore;
    }

    public String toString(){
        return this.insurer + " " + this.planname + " " + this.policyPrice + " " + this.scoreResult + " " + this.personalAcident + " " +
                this.medicalExpenses + " " + this.tripCancellation + " " + this.tripTerminaltion + " " + this.lossOfBaggageAndPersonal +
                this.reviewScore;
    }


}
