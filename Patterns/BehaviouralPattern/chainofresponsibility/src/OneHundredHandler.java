
class OneHundredHandler extends MoneyHandler{
    int totalNotes;

    public OneHundredHandler(int n){
        this.totalNotes = n;
    }

    @Override
    public void dispense(int amount){
        int required = amount/100;
        int used=0;
        if(required > this.totalNotes){
            used = totalNotes;
            this.totalNotes = 0;
        }else{

            used = required;
            this.totalNotes -= required;
        }

        if(used>0){
            System.out.println("Dispensing " + used + " x Rs. 100 notes.");
        }

        int remaining = amount - (used * 100);
        if (remaining > 0) {
            if (nextHandler != null) 
                nextHandler.dispense(remaining);
            else {
                System.out.println("Remaining amount of " + remaining + " cannot be fulfilled (Insufficinet fund in ATM)");
            }
        }
    }
}