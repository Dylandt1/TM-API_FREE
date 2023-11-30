package fr.tmmods.tmapi.mails;

public class Mail
{
    /**
     * This class is a mail object it is possible to use independently of other mails classes.
     */

    private final String of;
    private final String to;
    //private final String[] cc;
    private final String message;

    public Mail(String sender, String target, String message)
    {
        this.of = sender;
        this.to = target;
        //this.cc = otherTargets;
        this.message = message;
    }

    public String getSender() {return of;}
    public String getTarget() {return to;}
    //public String[] getOtherTargets() {return cc;}
    public String getMessage() {return message;}
}
