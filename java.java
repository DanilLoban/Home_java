import java.util.PriorityQueue;
import java.util.Queue;

public class program {
    private static class Toy implements Comparable<Toy> {
        private final int id;
        private final String name;
        private final int frequency;

        public Toy(int id, String name, int frequency) {
            this.id = id;
            this.name = name;
            this.frequency = frequency;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getFrequency() {
            return frequency;
        }

        @Override
        public int compareTo(Toy other) {
            // Сравниваем игрушки по их частоте выпадения (весу)
            return Integer.compare(this.frequency, other.frequency);
        }
    }

    private final Queue<Toy> toyQueue;

    public program(String toy1, String toy2, String toy3) {
        toyQueue = new PriorityQueue<>();

        Toy[] toys = new Toy[3];
        toys[0] = parseToy(toy1);
        toys[1] = parseToy(toy2);
        toys[2] = parseToy(toy3);

        // Добавляем игрушки в очередь
        for (Toy toy : toys) {
            toyQueue.add(toy);
        }
    }

    private Toy parseToy(String toyString) {
        String[] fields = toyString.split(",");
        int id = Integer.parseInt(fields[0]);
        String name = fields[1];
        int frequency = Integer.parseInt(fields[2]);

        return new Toy(id, name, frequency);
    }

    public void addToy(String toyString) {
        Toy toy = parseToy(toyString);
        toyQueue.add(toy);
    }

    public Queue<Toy> getToyQueue() {
        return toyQueue;
    }

    public static void main(String[] args) {
        // Пример использования класса ToyStore
        program toyStore = new program("1,Car,3", "2,Doll,5", "3,Robot,2");
        toyStore.addToy("4,Train,4");

        // Получаем игрушки из очереди в порядке увеличения частоты выпадения
        Queue<Toy> toyQueue = toyStore.getToyQueue();
        while (!toyQueue.isEmpty()) {
            Toy toy = toyQueue.poll();
            System.out.printf("Toy: %s (id: %d, frequency: %d)\n", toy.getName(), toy.getId(), toy.getFrequency());
        }
    }
}