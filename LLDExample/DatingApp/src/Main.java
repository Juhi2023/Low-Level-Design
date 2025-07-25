import java.util.*;

class Main{
    public static void main(String args[]){
        DatingApp app = DatingApp.getInstance();

        User user1 = app.createUser();
        User user2 = app.createUser();

        //set profile
        UserProfile profile1 =  user1.getProfile();
        profile1.setName("Rohan");
        profile1.setAge(28);
        profile1.setGender(Gender.MALE);
        profile1.setBio("I am a software developer.");
        profile1.addInterest("Travel");
        profile1.addInterest("Music");
        profile1.addInterest("Coding");
        Location location1 = new Location(1.03, 5);
        profile1.setLocation(location1);

        Preference pref1 = user1.getPreference();
        pref1.addGenderPreference(Gender.FEMALE);
        pref1.setMaxDistance(25.0);
        pref1.setAgeRange(25, 30);
        pref1.addInterest("Coding");
        pref1.addInterest("Travel");



        //set profile
        UserProfile profile2 =  user2.getProfile();
        profile2.setName("Riya");
        profile2.setAge(26);
        profile2.setGender(Gender.FEMALE);
        profile2.setBio("I am a Doctor.");
        profile2.addInterest("Travel");
        profile2.addInterest("Music");
        Location location2 = new Location(2.03, 5);
        profile2.setLocation(location2);

        Preference pref2 = user2.getPreference();
        pref2.addGenderPreference(Gender.MALE);
        pref2.setMaxDistance(15.0);
        pref2.setAgeRange(27, 30);
        pref2.addInterest("Painting");
        pref2.addInterest("Travel");

        // Display user profiles
        System.out.println("---- User Profiles ----");
        app.displayUser(user1.getId());
        app.displayUser(user2.getId());

        // Find nearby users for user1 (within 5km)
        System.out.println("\n---- Nearby Users for user1 (within 5km) ----");
        List<User> nearbyUsers = app.findNearbyUsers(user1.getId(), 5.0);
        System.out.println("Found " + nearbyUsers.size() + " nearby users");
        for (User user : nearbyUsers) {
            System.out.println("- " + user.getProfile().getName() + " (" + user.getId() + ")");
        }

        app.swipe(user1.getId(), user2.getId(), SwipeAction.RIGHT);
        app.swipe(user2.getId(), user1.getId(), SwipeAction.RIGHT);


        // Send messages in the chat room
        System.out.println("\n\n---- Chat Room ----");
        app.sendMessage(user1.getId(), user2.getId(), "Hi Neha, Kaise ho?");
        app.sendMessage(user2.getId(), user1.getId(), "Hi Rohan, Ma bdiya tum btao");
        app.displayChatRoom(user1.getId(), user2.getId());
    }
}

// -------------------- Observer Pattern -------------------- //

//observer
interface NotificationObserver{
    void update(String message);
}

class UserNotificationObserver implements NotificationObserver{
    String userId;

    public UserNotificationObserver(String userId){
        this.userId = userId;
    }

    public void update(String message){
        System.out.println("Notification for user " + userId + ": " + message);
    }
}

//observable
interface NotificationObservable{
    void addObserver(String id, NotificationObserver o);
    void removeObserver(String id);
    void notify(String id, String message);
    void notifyAll(String message);
}

class NotificationService implements NotificationObservable{
    Map<String, NotificationObserver> observers;

    private static NotificationService instance;

    private NotificationService() {
        observers = new HashMap<>();
    }

    public static NotificationService getInstance() {
        if (instance == null) {
            instance = new NotificationService();
        }
        return instance;
    }

    public void addObserver(String id, NotificationObserver o){
        observers.put(id, o);
    }

    public void removeObserver(String id){
        observers.remove(id);
    }

    public void notify(String id, String message){
        observers.get(id).update(message);
    }

    public void notifyAll(String message){
        for(String key: observers.keySet()){
            observers.get(key).update(message);
        }
    }
}

enum SwipeAction {
    LEFT,  // Dislike
    RIGHT  // Like
}

class User{
    static int userId=0;
    String id;
    UserProfile profile;
    Preference preference;
    Map<String, SwipeAction> swipeHistory;
    NotificationObserver observer;


    public User(){
        id = ++userId + "App";
        profile = new UserProfile();
        preference = new Preference();
        swipeHistory = new HashMap<>();
        observer = new UserNotificationObserver(id);
        NotificationService.getInstance().addObserver(id, observer);
    }

     public String getId() {
        return id;
    }

    public UserProfile getProfile() {
        return profile;
    }

    public Preference getPreference() {
        return preference;
    }

    public void swipe(String otherUserId, SwipeAction action) {
        swipeHistory.put(otherUserId, action);
    }

    public boolean hasLiked(String otherUserId) {
        return swipeHistory.containsKey(otherUserId) && swipeHistory.get(otherUserId) == SwipeAction.RIGHT;
    }

    public boolean hasDisliked(String otherUserId) {
        return swipeHistory.containsKey(otherUserId) && swipeHistory.get(otherUserId) == SwipeAction.LEFT;
    }

    public boolean hasInteractedWith(String otherUserId) {
        return swipeHistory.containsKey(otherUserId);
    }

    public void displayProfile() {  // Principle of least knowledge
        profile.display();
    }
}

class UserProfile{
    String name;
    Gender gender;
    int age;
    Location loc;
    String bio;
    List<String> photos;
    List<String> interests;

    public UserProfile() {
        name = "";
        age = 0;
        gender = Gender.OTHER;
        photos = new ArrayList<>();
        interests = new ArrayList<>();
        loc = new Location(0, 0);
    }

    public void setName(String n) {
        name = n;
    }

    public void setAge(int a) {
        age = a;
    }

    public void setGender(Gender g) {
        gender = g;
    }

    public void setBio(String b) {
        bio = b;
    }

    public void addPhoto(String photoUrl) {
        photos.add(photoUrl);
    }

    public void removePhoto(String photoUrl) {
        photos.remove(photoUrl);
    }

    public void addInterest(String name) {
        interests.add(name);
    }

    public void removeInterest(String name) {
        interests.remove(name);
    }

    public void setLocation(Location loc) {
        this.loc = loc;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    public String getBio() {
        return bio;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public List<String> getInterests() {
        return interests;
    }

    public Location getLocation() {
        return loc;
    }

    public void display() {
        System.out.println("===== Profile =====");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Gender: " + gender);
        System.out.println("Bio: " + bio);
        System.out.print("Photos: ");
        for (String photo : photos) {
            System.out.print(photo + ", ");
        }
        System.out.println();
        System.out.print("Interests: ");
        for (String i : interests) {
            System.out.print(i+", ");
        }
        System.out.println();
        System.out.println("Location: " + loc.getLatitude() + ", " + loc.getLongitude());
        System.out.println("===================");
    }

}

enum Gender{
    MALE,
    FEMALE,
    OTHER
}

class Location{
    double lan;
    double lon;

    public Location(double lan, double lon){
        this.lan = lan;
        this.lon = lon;
    }

    public double getLatitude(){
        return lan;
    }

    public double getLongitude(){
        return lon;
    }

    public double getDistanceTo(Location other){
        double x = lan - other.getLatitude();
        double y = lon - other.getLongitude();

        return (x*x) - (y*y);
    }
}

class Preference{
    int minAge;
    int maxAge;
    List<Gender> interestedIn;
    double maxDistance;
    List<String> interests;

        public Preference() {
        interestedIn = new ArrayList<>();
        interests = new ArrayList<>();
        minAge = 18;
        maxAge = 100;
        maxDistance = 100.0;
    }

    public void addGenderPreference(Gender gender) {
        interestedIn.add(gender);
    }

    public void removeGenderPreference(Gender gender) {
        interestedIn.remove(gender);
    }

    public void setAgeRange(int min, int max) {
        minAge = min;
        maxAge = max;
    }

    public void setMaxDistance(double distance) {
        maxDistance = distance;
    }

    public void addInterest(String interest) {
        interests.add(interest);
    }

    public void removeInterest(String interest) {
        interests.remove(interest);
    }

    public boolean isInterestedInGender(Gender gender) {
        return interestedIn.contains(gender);
    }

    public boolean isAgeInRange(int age) {
        return age >= minAge && age <= maxAge;
    }

    public boolean isDistanceAcceptable(double distance) {
        return distance <= maxDistance;
    }

    public List<String> getInterests() {
        return interests;
    }

    public List<Gender> getInterestedGenders() {
        return interestedIn;
    }

    public int getMinAge() {
        return minAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public double getMaxDistance() {
        return maxDistance;
    }
}


interface LocationStrategy{
    List<User> findNearbyUsers(Location loc, double maxDistance, List<User> allUsers);
}

class BasicLocationStrategy implements LocationStrategy{
    public List<User> findNearbyUsers(Location location, double maxDistance, List<User> allUsers){
        List<User> nearbyUsers = new ArrayList<>();
        for (User user : allUsers) {
            double distance = location.getDistanceTo(user.getProfile().getLocation());
            if (distance <= maxDistance) {
                nearbyUsers.add(user);
            }
        }
        return nearbyUsers;
    }
}

class LocationService{
    static LocationService instance;
    LocationStrategy strategy;

    private LocationService(){

    }

    public static LocationService getInstance(){
        if(instance==null){
            instance = new LocationService();
        }

        return instance; 
    }

    public void setLocationStrategy(LocationStrategy s){
        strategy = s;
    }

    public List<User> findNearbyUsers(Location loc, double maxDistance, List<User> allUsers){
        return strategy.findNearbyUsers(loc, maxDistance, allUsers);
    }

}



// -------------------- Matching System -------------------- //
enum MatcherType {
    BASIC,
    INTERESTS_BASED,
    LOCATION_BASED
}

// Matcher interface
interface Matcher {
    double calculateMatchScore(User user1, User user2);
}

class BasicMatcher implements Matcher{
    public double calculateMatchScore(User user1, User user2){
        //check gender preference
        boolean user1likeUser2Gender = user1.getPreference().isInterestedInGender(user2.getProfile().getGender());
        boolean user2likeUser1Gender = user2.getPreference().isInterestedInGender(user1.getProfile().getGender());

        if(!user1likeUser2Gender || !user2likeUser1Gender){
            return 0.0;
        }

        //check age preference
        boolean user1MatchUser2AgeRange = user1.getPreference().isAgeInRange(user2.getProfile().getAge());
        boolean user2MatchUser1AgeRange = user2.getPreference().isAgeInRange(user1.getProfile().getAge());

        if(!user1MatchUser2AgeRange || !user2MatchUser1AgeRange){
            return 0.0;
        }

        // Check distance preference
        double distance = user1.getProfile().getLocation().getDistanceTo(user2.getProfile().getLocation());
        boolean user1LikesUser2Distance = user1.getPreference().isDistanceAcceptable(distance);
        boolean user2LikesUser1Distance = user2.getPreference().isDistanceAcceptable(distance);

        if (!user1LikesUser2Distance || !user2LikesUser1Distance) {
            return 0.0;
        }

        // If all basic criteria match, return a base score
        return 0.5; // 50% match
    }
}

class InterestsBasedMatcher implements Matcher {
    public double calculateMatchScore(User user1, User user2) {
        // First, check basic compatibility
        BasicMatcher basicMatcher = new BasicMatcher();
        double baseScore = basicMatcher.calculateMatchScore(user1, user2);

        if (baseScore == 0.0) {
            return 0.0; 
        }

        // Calculate score based on shared interests
        List<String> user1InterestNames = user1.getProfile().getInterests();

        int sharedInterests = 0;
        for (String interest : user2.getProfile().getInterests()) {
            if (user1InterestNames.contains(interest)) {
                sharedInterests++;
            }
        }

        // Bonus score based on shared interests (up to 0.5 additional points)
        double maxInterests = Math.max(user1.getProfile().getInterests().size(), user2.getProfile().getInterests().size());
        double interestScore = maxInterests > 0 ? 0.5 * ((double) sharedInterests / maxInterests) : 0.0;

        return baseScore + interestScore;
    }
}

class LocationBasedMatcher implements Matcher {
    public double calculateMatchScore(User user1, User user2) {
        // First, check basic compatibility
        InterestsBasedMatcher interestsMatcher = new InterestsBasedMatcher();
        double baseScore = interestsMatcher.calculateMatchScore(user1, user2);

        if (baseScore == 0.0) {
            return 0.0; 
        }

        // Calculate score based on proximity
        double distance = user1.getProfile().getLocation().getDistanceTo(user2.getProfile().getLocation());
        double maxDistance = Math.min(user1.getPreference().getMaxDistance(), user2.getPreference().getMaxDistance());

        // Closer is better, score decreases with distance (up to 0.2 additional points)
        double proximityScore = maxDistance > 0 ? 0.2 * (1.0 - (distance / maxDistance)) : 0.0;

        return baseScore + proximityScore;
    }
}

class MatcherFactory {
    public static Matcher createMatcher(MatcherType type) {
        switch (type) {
            case BASIC:
                return new BasicMatcher();
            case INTERESTS_BASED:
                return new InterestsBasedMatcher();
            case LOCATION_BASED:
                return new LocationBasedMatcher();
            default:
                return new BasicMatcher();
        }
    }
}

// -------------------- Message System -------------------- //

// Message class
class Message {
    private String senderId;
    private String content;
    private long timestamp;

    public Message(String sender, String msg) {
        senderId = sender;
        content = msg;
        timestamp = System.currentTimeMillis();
    }

    public String getSenderId() {
        return senderId;
    }

    public String getContent() {
        return content;
    }

    public long getTimestamp() {
        return timestamp;
    }
}


class ChatRoom {
    private String id;
    private List<String> participantIds;
    private List<Message> messages;

    public ChatRoom(String roomId, String user1Id, String user2Id) {
        id = roomId;
        participantIds = new ArrayList<>();
        participantIds.add(user1Id);
        participantIds.add(user2Id);
        messages = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void addMessage(String senderId, String content) {
        Message msg = new Message(senderId, content);
        messages.add(msg);
    }

    public boolean hasParticipant(String userId) {
        return participantIds.contains(userId);
    }

    public List<Message> getMessages() {
        return messages;
    }

    public List<String> getParticipants() {
        return participantIds;
    }

    public void displayChat() {
        System.out.println("===== Chat Room: " + id + " =====");
        for (Message msg : messages) {
            System.out.println("[" + msg.getTimestamp() + "] " + msg.getSenderId() + ": " + msg.getContent());
        }
        System.out.println("=========================");
    }
}

// -------------------- Dating App -------------------- //

// Facade Pattern: Dating app system
class DatingApp{
    static DatingApp instance;

    List<User> users;
    List<ChatRoom> chatRooms;
    Matcher matcher;

    private DatingApp(){
        users = new ArrayList<>();
        chatRooms = new ArrayList<>();
        matcher = MatcherFactory.createMatcher(MatcherType.LOCATION_BASED);
    }

    public static DatingApp getInstance(){
        if(instance==null){
            instance = new DatingApp();
        }
        return instance;
    }

    public void setMatcher(MatcherType type){
        this.matcher = MatcherFactory.createMatcher(type);
    }

    public User createUser(){
        User user = new User();
        users.add(user);
        return user;
    }

    public User getUser(String id){
        for(User u: users){
            if(u.getId().equals(id)){
                return u;
            }
        }
        return null;
    }

     public List<User> findNearbyUsers(String userId, double maxDis){
        User user = getUser(userId);
        LocationService.getInstance().setLocationStrategy(new BasicLocationStrategy());
        List<User> nearByUsers = LocationService.getInstance().findNearbyUsers(user.getProfile().getLocation(), maxDis, users); 

        // Filter out the user themselves        
        nearByUsers.remove(user);

        //Filter out users that doesnt match preference or has already been swiped
        List<User> filteredUser = new ArrayList<>();
        for(User other: nearByUsers){
            if(!user.hasInteractedWith(other.getId())){
                double score = matcher.calculateMatchScore(user, other);
                if(score>0.0){
                    filteredUser.add(other);
                }
            }
        }
        return filteredUser;
    }

    public boolean swipe(String userId, String targetUserId, SwipeAction action){
        User user = getUser(userId);
        User targetUser = getUser(targetUserId);

        if (user == null || targetUser == null) {
            System.out.println("User not found.");
            return false;
        }

        user.swipe(targetUserId, action);

        //check if its match
        if(action == SwipeAction.RIGHT && targetUser.hasLiked(userId)){
            System.out.println("Match Found.");
            String roomId = userId+"_"+ targetUserId;
            ChatRoom room = new ChatRoom(roomId, userId, targetUserId);
            chatRooms.add(room);

            //Notify
            NotificationService.getInstance().notify(userId, "You have a new match with "+ user.getProfile().getName()+ "!");
            NotificationService.getInstance().notify(targetUserId, "You have a new match with "+ targetUser.getProfile().getName()+ "!");
            return true;
        }
        return false;
    }

    public ChatRoom getChatRoom(String user1Id, String user2Id) {
        for (ChatRoom chatRoom : chatRooms) {
            if (chatRoom.hasParticipant(user1Id) && chatRoom.hasParticipant(user2Id)) {
                return chatRoom;
            }
        }
        return null;
    }


    public void sendMessage(String senderId, String recieverId, String content){
        ChatRoom room = getChatRoom(senderId, recieverId);
        room.addMessage(senderId, content);
        NotificationService.getInstance().notify(recieverId, "New message from "+ getUser(senderId).getProfile().getName());
    }

    public void displayUser(String userId) {
        User user = getUser(userId);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }
        user.displayProfile();
    }


    public void displayChatRoom(String user1Id, String user2Id) {
        ChatRoom chatRoom = getChatRoom(user1Id, user2Id);
        if (chatRoom == null) {
            System.out.println("No chat room found between these users.");
            return;
        }
        chatRoom.displayChat();
    }
}


