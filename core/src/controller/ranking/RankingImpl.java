package controller.ranking;

import java.io.*;
import java.net.URLDecoder;
import java.util.*;
import java.util.stream.Collectors;

public class RankingImpl implements Ranking{

    private Map<String, Integer> map;
    private File file;
    private String filePath;

    /**
     * Constructor.
     *
     * @throws IOException
     */
    public RankingImpl() throws IOException {
        this.map = new HashMap<>();
        this.filePath = "Ranking.txt";
        this.filePath = URLDecoder.decode(this.filePath, "UTF-8");
        this.file = new File(this.filePath);
        this.loadFromFile();
    }

    /**
     * Load scores from file.
     * If file doesn't exist, create a new one.
     *
     * @throws IOException
     */
    private void loadFromFile() throws IOException {
        if(this.file.exists()) {
            this.map.clear();
            Properties properties = new Properties();
            properties.load(new FileInputStream(this.file));
            for (String key : properties.stringPropertyNames()) {
                String value = properties.getProperty(key);
                this.map.put(key, Integer.valueOf(value));
            }
        } else {
            this.file.createNewFile();
            this.saveToFile();
        }
    }

    /**
     * Saves scores to file.
     *
     * @throws IOException
     */
    private void saveToFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(this.file));
        this.map.entrySet().forEach(e -> {
            try {
                writer.write(e.getKey() + "=" + e.getValue().toString() + "\n");
                writer.newLine();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        writer.close();
    }

    @Override
    public void addPlayer(String playerNickname, Integer playerScore) throws IOException {
        if (this.map.containsKey(playerNickname) && (playerScore <= this.map.get(playerNickname))) {
            return;
        }
        this.map.put(playerNickname, playerScore);
        this.saveToFile();
    }

    /**
     * Sort a Map<String, Integer> into a List<Entry<String, Integer>> by value.
     * Example: Map{(a,1), (b,4)} -> List{(b,4), (a,1)}
     *
     * @param unsortedMap to sort
     * @return an ordered list containing the sorted values of the map
     */
    private List<Map.Entry<String, Integer>> getSortedList(final Map<String, Integer> unsortedMap) {
        return unsortedMap.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }

    @Override
    public String getFormattedRanking(int limit) {
        StringJoiner joiner = new StringJoiner("");
        List<Map.Entry<String, Integer>> sortedEntry;
        sortedEntry = this.getSortedList(this.map).stream().limit(limit).collect(Collectors.toList());
        sortedEntry.forEach(e -> joiner.add(e.getValue().toString() + "\t" + e.getKey() + "\n"));
        return joiner.toString();
    }
}
