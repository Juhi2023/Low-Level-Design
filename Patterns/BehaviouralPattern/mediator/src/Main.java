class Main{
    public static void main(String args[]){
        IMediator chatM = new ChatMediator();

        User u1 = new User("Juhi", chatM);
        User u2 = new User("Shobha", chatM);
        User u3 = new User("Nandini", chatM);

        chatM.registerUser(u1);
        chatM.registerUser(u2);
        chatM.registerUser(u3);

        u1.send("HI, How are you All?");
        u3.sendPrivate("Shobha", "HI Shobha");
    }
}