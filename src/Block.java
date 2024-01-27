import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

class Block {
  private int index;
  private long timestamp;
  private String data;
  private String previousHash;
  private String hash;

  // Konstruktor
  public Block(int index, long timestamp, String data, String previousHash) {
    this.index = index;
    this.timestamp = timestamp;
    this.data = data;
    this.previousHash = previousHash;
    this.hash = calculateHash();
  }

  // Hash-Berechnung mit SHA-256
  private String calculateHash() {
    String dataToHash = index + timestamp + data + previousHash;
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      byte[] hashBytes = digest.digest(dataToHash.getBytes());
      StringBuilder hexString = new StringBuilder();
      for (byte hashByte : hashBytes) {
        String hex = Integer.toHexString(0xff & hashByte);
        if (hex.length() == 1) hexString.append('0');
        hexString.append(hex);
      }
      return hexString.toString();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      return null;
    }
  }

  public String getHash() {
    return hash;
  }

  public int getIndex() {
    return index;
  }

  public void setIndex(int index) {
    this.index = index;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }

  public String getPreviousHash() {
    return previousHash;
  }

  public void setPreviousHash(String previousHash) {
    this.previousHash = previousHash;
  }

  public void setHash(String hash) {
    this.hash = hash;
  }
}

class Blockchain {
  private List<Block> chain;

  // Konstruktor
  public Blockchain() {
    chain = new ArrayList<>();
    // Genesis-Block erstellen
    createGenesisBlock();
  }

  // Genesis-Block erstellen
  private void createGenesisBlock() {
    Block genesisBlock = new Block(0, System.currentTimeMillis(), "Genesis Block", "0");
    chain.add(genesisBlock);
  }

  // Neuen Block hinzufügen
  public void addBlock(String data) {
    Block latestBlock = getLatestBlock();
    Block newBlock = new Block(latestBlock.getIndex() + 1, System.currentTimeMillis(), data, latestBlock.getHash());
    chain.add(newBlock);
  }

  // Letzten Block abrufen
  private Block getLatestBlock() {
    return chain.get(chain.size() - 1);
  }

  public List<Block> getChain() {
    return chain;
  }

  public void setChain(List<Block> chain) {
    this.chain = chain;
  }
}
