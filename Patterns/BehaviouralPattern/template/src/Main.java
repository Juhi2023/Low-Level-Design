public class Main {
    public static void main(String[] args) {
        System.out.println("----------------- Neural Network Training -----------------");
        ModelTrainer nnTrainer = new NeuralNetworkTrainer();
        nnTrainer.trainPipeline("data.csv");

        System.out.println("\n----------------- Decision Tree Training -----------------");
        ModelTrainer dtTrainer = new DecisionTreeTrainer();
        dtTrainer.trainPipeline("data.csv");
    }
}