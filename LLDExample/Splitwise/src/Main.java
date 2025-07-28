import java.util.*;

class Main{
    public static void main(String args[]){
        Splitwise manager = Splitwise.getInstance();
        
        System.out.println("\n=========== Creating Users ====================");
        User user1 = manager.createUser("Aditya");
        User user2 = manager.createUser("Rohit");
        User user3 = manager.createUser("Manish");
        User user4 = manager.createUser("Saurav");
        
        System.out.println("\n=========== Creating Group and Adding Members ====================");
        Group hostelGroup = manager.createGroup("Hostel Expenses");
        manager.addUserToGroup(user1.userId, hostelGroup.groupId);
        manager.addUserToGroup(user2.userId, hostelGroup.groupId);
        manager.addUserToGroup(user3.userId, hostelGroup.groupId);
        manager.addUserToGroup(user4.userId, hostelGroup.groupId);

        System.out.println("\n=========== Adding Expenses in group ====================");    
        List<String> groupMembers = Arrays.asList(user1.userId, user2.userId, user3.userId, user4.userId);
        manager.addExpenseToGroup(hostelGroup.groupId, "Lunch", 800.0, user1.userId, groupMembers, SplitType.EQUAL, new ArrayList<>());
        
        List<String> dinnerMembers = Arrays.asList(user1.userId, user3.userId, user4.userId);
        List<Double> dinnerAmounts = Arrays.asList(200.0, 300.0, 200.0);
        manager.addExpenseToGroup(hostelGroup.groupId, "Dinner", 700.0, user3.userId, dinnerMembers, SplitType.UNEQUAL, dinnerAmounts);

        System.out.println("\n=========== printing Group-Specific Balances ===================="); 
        manager.showGroupBalances(hostelGroup.groupId);

        System.out.println("\n=========== Debt Simplification ===================="); 
        manager.simplifyGroupDebts(hostelGroup.groupId);

        System.out.println("\n=========== printing Group-Specific Balances ===================="); 
        manager.showGroupBalances(hostelGroup.groupId);

        System.out.println("\n=========== Adding Individual Expense ===================="); 
        manager.addIndividualExpense("Coffee", 40.0, user2.userId, user4.userId, SplitType.EQUAL);
        
        System.out.println("\n=========== printing User Balances ===================="); 
        // manager.showUserBalance(user1.userId);
        // manager.showUserBalance(user2.userId);
        // manager.showUserBalance(user3.userId);
        // manager.showUserBalance(user4.userId);

        System.out.println("\n==========Attempting to remove Rohit from group==========");
        manager.removeUserFromGroup(user2.userId, hostelGroup.groupId);

        System.out.println("\n======== Making Settlement to Clear Rohit's Debt =========="); 
        manager.settlePaymentInGroup(hostelGroup.groupId, user2.userId, user3.userId, 200.0);
        
        System.out.println("\n======== Attempting to Remove Rohit Again ==========");
        manager.removeUserFromGroup(user2.userId, hostelGroup.groupId);
        
        System.out.println("\n=========== Updated Group Balances ===================="); 
        manager.showGroupBalances(hostelGroup.groupId);
    }
}

interface Observer {
    void update(String message);
}

class User implements Observer{
    static int nextId=0;
    public String userId;
    public String name;
    public Map<String, Double> balances; // userId -> amount (positive = they owe you, negative = you owe them)

    public User(String name){
        this.userId = "user"+ ++nextId;
        this.name=name;
    }

    public String getId(){
        return userId;
    }

    public String getName(){
        return name;
    }

    public void updateBalance(String otherUserId, double amount) {
        balances.put(otherUserId, balances.getOrDefault(otherUserId, 0.0) + amount);
        
        // Remove if balance becomes zero
        if (Math.abs(balances.get(otherUserId)) < 0.01) {
            balances.remove(otherUserId);
        }
    }

    @Override
    public void update(String message) {
        System.out.println("[NOTIFICATION to " + name + "]: " + message);
    }
}

class Expense {
    public static int nextExpenseId = 0;
    public String expenseId;
    public String description;
    public double totalAmount;
    public String paidByUserId;
    public List<Split> splits;
    public String groupId;
    
    public Expense(String desc, double amount, String paidBy, List<Split> splits, String group) {
        this.expenseId = "expense" + (++nextExpenseId);
        this.description = desc;
        this.totalAmount = amount;
        this.paidByUserId = paidBy;
        this.splits = splits;
        this.groupId = group;
    }
    
    public Expense(String desc, double amount, String paidBy, List<Split> splits) {
        this(desc, amount, paidBy, splits, "");
    }
}

class Split {
    public String userId;
    public double amount;
    
    public Split(String userId, double amount) {
        this.userId = userId;
        this.amount = amount;
    }
}

class Group{
    public static int nextId=0;
    public String groupId;
    public String name;
    public List<User> members; //observers
    public List<Expense> expenses;
    public Map<String, Map<String, Double>> balanceSheet;


    public Group(String name){
        this.groupId = "group"+ ++nextId;
        this.name = name;
        this.members = new ArrayList<>();
        this.expenses = new ArrayList<>();
        this.balanceSheet = new HashMap<>();
    }

    public void addMember(User u){
        members.add(u);
    }

    public boolean removeMember(String userId){
        if(canUserLeaveGroup(userId)){
            int ind=0;
            for(int i=0; i<members.size(); i++){
                if(userId.equals(members.get(i).getId())){
                    ind=i;
                }
            }
            members.remove(ind);
            balanceSheet.remove(userId);
            for(String key:  balanceSheet.keySet()){
                balanceSheet.get(key).remove(userId);
            }
        }else{
            System.out.println("You cant leave group without settlement.");
            return false;
        }
        return true;
    }

    public void notifyMembers(String message) {
        for (Observer observer : members) {
            observer.update(message);
        }
    }

    public boolean addExpense(String userId, String description, double totalAmount, SplitType type, List<String> users, List<Double> splitValues ){
        if(!isMember(userId)){
            return false;
        }

        SplitStrategy strategy = SplitFactory.createSplitStrategy(type);
        List<Split> splits = strategy.calculateSplit(totalAmount, users, splitValues);
        expenses.add(new Expense(description, totalAmount, userId, splits, groupId));

        // Update group balances
        for (Split split : splits) {
            if (!split.userId.equals(userId)) {
                // Person who paid gets positive balance, person who owes gets negative
                updateBalanceSheet(userId, split.userId, split.amount);
            }
        }

        //Sending notification
        String paidByName = getUserByuserId(userId).name;
        notifyMembers("New expense added: " + description + " (Rs " + totalAmount + ")");
        
        // Printing console message-------
        System.out.println("\n=========== Expense Message ====================");
        System.out.println("Expense added to " + name + ": " + description + " (Rs " + totalAmount + ") paid by " + paidByName +" and involved people are : ");
        for(int i=0; i<splitValues.size(); i++) {
            System.out.println(getUserByuserId(users.get(i)).getName() + " : " + splitValues.get(i));
        }
        
        return true;
        
    }

    public void simplifyGroupDebts() {
        Map<String, Map<String, Double>> simplifiedBalances = DebtSimplifier.simplifyDebts(balanceSheet);
        balanceSheet = simplifiedBalances;
        System.out.println("\nDebts have been simplified for group: " + name);
    }

    public void showGroupBalances() {
        System.out.println("\n=== Group Balances for " + name + " ===");
        
        for (Map.Entry<String, Map<String, Double>> pair : balanceSheet.entrySet()) {
            String memberId = pair.getKey();
            String memberName = getUserByuserId(memberId).name;

            System.out.println(memberName + "'s balances in group:");
            
            Map<String, Double> userBalances = pair.getValue();
            if (userBalances.isEmpty()) {
                System.out.println("  No outstanding balances");
            } 
            else {
                for (Map.Entry<String, Double> userBalance : userBalances.entrySet()) {
                    String otherMemberUserId = userBalance.getKey();
                    String otherName = getUserByuserId(otherMemberUserId).name;
                    
                    double balance = userBalance.getValue();
                    if (balance > 0) {
                        System.out.println("  " + otherName + " owes: Rs " + balance);
                    } else {
                        System.out.println("  Owes " + otherName + ": Rs " + Math.abs(balance));
                    }
                }
            }
        }
    }

    public boolean settlePayment(String fromUserId, String toUserId, double amount) {
        // Validate that both users are group members
        if (!isMember(fromUserId) || !isMember(toUserId)) {
            System.out.println("user is not a part of this group");
            return false;
        }
        
        // Update group balances
        updateBalanceSheet(fromUserId, toUserId, amount);
        
        // Get user names for display
        String fromName = getUserByuserId(fromUserId).name;
        String toName = getUserByuserId(toUserId).name;
        
        // Notify group members
        notifyMembers("Settlement: " + fromName + " paid " + toName + " Rs " + amount);
        
        System.out.println("Settlement in " + name + ": " + fromName + " settled Rs "  + amount + " with " + toName);
        
        return true;
    }



    //helper functions
    private User getUserByuserId(String userId) {
        User user = null;

        for(User member : members) {
            if(member.userId.equals(userId)) {
                user = member;
            }
        }
        return user;
    }

    public boolean canUserLeaveGroup(String userId){
        if (!isMember(userId)) {
            System.out.println("user is not a part of this group");
            return false;
        }
        
        // Check if user has any outstanding balance with other group members
        Map<String, Double> userBalanceSheet = balanceSheet.get(userId);
        for (String id : userBalanceSheet.keySet()) {
            if (Math.abs(userBalanceSheet.get(id)) > 0.01) {
                return false; 
            }
        }
        return true;
    }

    public boolean isMember(String id){
        for(User u: members){
            if(u.getId().equals(id)){
                return true;
            }
        }

        return false;
    }

    public void updateBalanceSheet(String fromUserId, String toUserId, double amount){
        balanceSheet.get(fromUserId).put(toUserId, balanceSheet.get(fromUserId).getOrDefault(toUserId, 0.0) + amount);
        balanceSheet.get(toUserId).put(fromUserId, balanceSheet.get(toUserId).getOrDefault(fromUserId, 0.0) - amount);
        
        // Remove if balance becomes zero
        if (Math.abs(balanceSheet.get(fromUserId).get(toUserId)) < 0.01) {
            balanceSheet.get(fromUserId).remove(toUserId);
        }
        if (Math.abs(balanceSheet.get(toUserId).get(fromUserId)) < 0.01) {
            balanceSheet.get(toUserId).remove(fromUserId);
        }
    }
}

//--------------Debit Simplifier----------------------
class DebtSimplifier{
    public static Map<String, Map<String, Double>> simplifyDebts(Map<String, Map<String, Double>> balanceSheet){

        //Step 1: Calculate amount for each person
        Map<String, Double> netAmount = new HashMap<>();
        for(String key: balanceSheet.keySet()){
            netAmount.put(key, 0.0);
        }

        for(String key: balanceSheet.keySet()){
            String userId = key;
            for(String subKey: balanceSheet.get(key).keySet()){
                String otherUserId = subKey;
                double amount = balanceSheet.get(key).get(subKey);      

                if(amount>0){
                    netAmount.put(userId, netAmount.get(userId)+amount);
                    netAmount.put(otherUserId, netAmount.get(otherUserId)-amount);
                }

            }
        }

        //Step 2: Divide users into creditors and debtors
        List<Pair> creditors = new ArrayList<>(); // those who should receive money
        List<Pair> debtors = new ArrayList<>();   // those who should pay money
        
        for (String key : netAmount.keySet()) {
            double amount = netAmount.get(key);
            if (amount > 0.01) { // creditor
                creditors.add(new Pair(key, amount));
            } else if (amount < -0.01) { // debtor
                debtors.add(new Pair(key, -amount)); // store positive amount
            }
        }

        //Step 3: Sort them
        creditors.sort((a, b)-> Double.compare(b.amount, a.amount));
        debtors.sort((a, b)-> Double.compare(b.amount, a.amount));

        //Step 4:  Initialize empty maps for all users
        Map<String, Map<String, Double>> result = new HashMap<>();
        for (String key : balanceSheet.keySet()) {
            result.put(key, new HashMap<>());
        }

        //Step 5: Use greedy algorithm to minimize transactions
        int i=0, j=0;
        while(i<creditors.size() && j<debtors.size()){
            String creditorId = creditors.get(i).id;
            String debtorId = debtors.get(j).id;
            double creditorAmount = creditors.get(i).amount;
            double debtorAmount = debtors.get(j).amount;

            // Find the minimum amount to settle
            double settleAmount = Math.min(creditorAmount, debtorAmount);
            result.get(creditorId).put(debtorId, settleAmount);
            result.get(debtorId).put(creditorId, -settleAmount);

            // Update remaining amounts
            creditors.get(i).amount = creditorAmount - settleAmount;
            debtors.get(j).amount = debtorAmount - settleAmount;

            // Move to next creditor or debtor if current one is settled
            if (creditors.get(i).amount < 0.01) {
                i++;
            }
            if (debtors.get(j).amount < 0.01) {
                j++;
            }
        }
        return result;
    }
}

class Pair{
    public String id;
    public Double amount;

    public Pair(String id, Double amount){
        this.id = id;
        this.amount = amount;
    }
}


//--------------Split Strategy----------------------
enum SplitType{
    EQUAL,
    UNEQUAL,
    PERCENTAGE
}

interface SplitStrategy {
    List<Split> calculateSplit(double totalAmount, List<String> userIds, List<Double> values);
}

class EqualSplit implements SplitStrategy {
    public List<Split> calculateSplit(double totalAmount, List<String> userIds, List<Double> values){
        List<Split> res= new ArrayList<>();
        for(String x: userIds){
            res.add(new Split(x, totalAmount/userIds.size()));
        }
        return res;
    }
}

class UnequalSplit implements SplitStrategy {
    public List<Split> calculateSplit(double totalAmount, List<String> userIds, List<Double> values){
        List<Split> res= new ArrayList<>();
        for(int i=0; i<userIds.size(); i++){
            res.add(new Split(userIds.get(i), values.get(i)));
        }
        return res;
    }
}

class PercentageSplit implements SplitStrategy {
    public List<Split> calculateSplit(double totalAmount, List<String> userIds, List<Double> values){
        List<Split> res= new ArrayList<>();
        for(int i=0; i<userIds.size(); i++){
            res.add(new Split(userIds.get(i), totalAmount * values.get(i) /100));
        }
        return res;
    }
}

class SplitFactory{
    public static SplitStrategy createSplitStrategy(SplitType type){
        switch(type){
            case SplitType.EQUAL: return new EqualSplit();
            case SplitType.UNEQUAL: return new UnequalSplit();
            case SplitType.PERCENTAGE: return new PercentageSplit();
            default: return null;
        }
    }
}


//----------------App------------------
class Splitwise {
    private Map<String, User> users;
    private Map<String, Group> groups;

    private static Splitwise instance;
    
    private Splitwise() {
        users = new HashMap<>();
        groups = new HashMap<>();
    }

    public static Splitwise getInstance() {
        if(instance == null) {
            instance = new Splitwise();
        }
        return instance;
    }

    // User management
    public User createUser(String name) {
        User user = new User(name);
        users.put(user.getId(), user);
        System.out.println("User created: " + name + " (ID: " + user.getId() + ")");
        return user;
    }

    //Group Management
    public Group createGroup(String name) {
        Group group = new Group(name);
        groups.put(group.groupId, group);
        System.out.println("Group created: " + name + " (ID: " + group.groupId + ")");
        return group;
    }

    public void addUserToGroup(String userId, String groupId) {
        User user = users.get(userId);
        Group group = groups.get(groupId);
        
        if (user != null && group != null) {
            group.addMember(user);
        }
    }

    public boolean removeUserFromGroup(String userId, String groupId) {
        Group group = groups.get(groupId);
        
        if (group == null) {
            System.out.println("Group not found!");
            return false;
        }
        
        User user = users.get(userId);
        if (user == null) {
            System.out.println("User not found!");
            return false;
        }

        boolean userRemoved = group.removeMember(userId);
        
        if(userRemoved) {
            System.out.println(user.name + " successfully left " + group.name);
        }
        return userRemoved;
    }

    //Expense management
    public void addExpenseToGroup(String groupId, String description, double amount, String paidByUserId, List<String> involvedUsers,  SplitType splitType, List<Double> splitValues) {

        Group group = groups.get(groupId);
        if (group == null) {
            System.out.println("Group not found!");
            return;
        }
        
        group.addExpense(paidByUserId, description, amount, splitType, involvedUsers, splitValues);
    }

    // Settlement - delegate to group
    public void settlePaymentInGroup(String groupId, String fromUserId, String toUserId, double amount) {
        
        Group group = groups.get(groupId);
        if (group == null) {
            System.out.println("Group not found!");
            return;
        }
        
        group.settlePayment(fromUserId, toUserId, amount);
    }
    
    // Settlement
    public void settleIndividualPayment(String fromUserId, String toUserId, double amount) {
        User fromUser = users.get(fromUserId);
        User toUser = users.get(toUserId);
        
        if (fromUser != null && toUser != null) {
            fromUser.updateBalance(toUserId, amount);
            toUser.updateBalance(fromUserId, -amount);
            
            System.out.println(fromUser.name + " settled Rs" + amount + " with " + toUser.name);
        }
    }
    
    public void addIndividualExpense(String description, double amount, String paidByUserId, String toUserId, SplitType splitType, List<Double> splitValues) {

        SplitStrategy strategy = SplitFactory.createSplitStrategy(splitType);
        List<Split> splits = strategy.calculateSplit(amount, Arrays.asList(paidByUserId, toUserId), splitValues);
        
        User paidByUser = users.get(paidByUserId);
        User toUser = users.get(toUserId);

        paidByUser.updateBalance(toUserId, amount);
        toUser.updateBalance(paidByUserId, -amount);
        
        System.out.println("Individual expense added: " + description + " (Rs " + amount  + ") paid by " + paidByUser.name +" for " + toUser.name);
    }
    
    public void addIndividualExpense(String description, double amount, String paidByUserId,String toUserId, SplitType splitType) {
        addIndividualExpense(description, amount, paidByUserId, toUserId, splitType, new ArrayList<>());
    }


    //Display Method
    public void showGroupBalances(String groupId) {
        Group group = groups.get(groupId);
        if (group == null) return;
        
        group.showGroupBalances();
    }

    public void simplifyGroupDebts(String groupId) {
        Group group = groups.get(groupId);
        if (group == null) return;
                
        // Use group's balance data for debt simplification
        group.simplifyGroupDebts();
    }
}