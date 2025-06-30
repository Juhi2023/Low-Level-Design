class User implements IColleague{
    String name;
    IMediator mediator;

    public User(String name, IMediator mediator){
        this.name = name;
        this.mediator = mediator;
    }

    public String getName(){
        return name;
    }

    public void send(String msg){
        mediator.send(name, msg);
    }

    public void sendPrivate(String to, String msg){
        mediator.sendPrivate(name, to, msg);
    }

    public void recieve(String from, String msg){
        System.out.println(from + " - " + name + " [Private]: " + msg);
    }
}