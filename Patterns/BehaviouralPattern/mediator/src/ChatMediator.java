import java.util.*;

public class ChatMediator implements IMediator{
    List<User> users = new ArrayList<>();
    HashMap<String, HashSet<String>> mutes = new HashMap<>();

    public void registerUser(User u){
        users.add(u);
    }

    public void mute(String who, String whom){
        if(!mutes.containsKey(who)){
            mutes.put(who, new HashSet<>());
        }
        mutes.get(who).add(whom);
    }

    public void send(String from, String msg){
        System.out.println(from + " [Broadcast]: " + msg);
        for(User u: users){
            if(from != u.getName()){
                boolean isMuted = mutes.containsKey(u.getName()) && mutes.get(u.getName()).contains(from);
                if(!isMuted){
                    u.recieve(from, msg);
                }
            }
        }
    }

    public void sendPrivate(String from, String to, String msg){
        for(User u: users){
            if(to == u.getName()){
                boolean isMuted = mutes.containsKey(to) && mutes.get(to).contains(from);
                if(!isMuted){
                    u.recieve(from, msg);
                }
            }
        }
    }
}