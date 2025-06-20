public non-sealed class USClock extends Clock{

    private String periodIndicator;

    public String getPeriodIndicator() {
        return periodIndicator;
    }

    public void setAfterMidDay(){
        this.periodIndicator = "PM";
    }

    public void setBeforeMidDay(){
        this.periodIndicator = "AM";
        
    }
    public void setHour(int hour) {
        setBeforeMidDay();
        if ((hour >= 12) && (hour <=23)){
            setAfterMidDay();
            this.hour = hour - 12;
        }
        else if (hour >=24) {
            this.periodIndicator = "AM";
            this.hour = 0;
        }
        else {
            this.hour = hour;
        }
    }

    @Override

    public Clock convert (final Clock clock) {
        this.second = clock.getSecond();
        this.minute = clock.getMinute();
                switch (clock) {
            case USClock usClock -> {
                this.hour = usClock.getHour();
                this.periodIndicator = usClock.getPeriodIndicator();
            }
            case BRLClock brlClock -> {
                this.setHour(brlClock.getHour());
            }
        }
        return this;
    }

    @Override
    public String getTime() {
        return super.getTime() + " " + this.periodIndicator;
    }

}
