package dispenser;

import java.util.*;

public class NoteDispenser implements IDispenser{
    IDispenser next;
    int noteValue;
    int totalNotes;

    public NoteDispenser(int noteValue, int totalNotes){
        this.noteValue = noteValue;
        this.totalNotes = totalNotes;
    }

    public void setNextDispenser(IDispenser next){
        this.next = next;
    }

    public void dispene(int amount){
        if(amount>=noteValue){
            int requiredNote = Math.min(amount/noteValue, totalNotes);
            int remainingAmount = amount - (requiredNote*noteValue);
            if(requiredNote>0){
                System.out.println("Dispensing " + requiredNote + " x $" + noteValue + " note(s)");
                totalNotes -= requiredNote;
            }
            if(remainingAmount>0 && next!=null){
                next.dispene(remainingAmount);
            }
        }else if(next!=null){
            next.dispene(amount);
        }
    }

    public boolean canDispense(int amount){
        if(amount==0){
            return true;
        }
        int requiredNote = Math.min(amount/noteValue, totalNotes);
        int remainingAmount = amount - (requiredNote*noteValue);
            
        if(next!=null){
            return next.canDispense(amount);
        }
        return false;
    }

}