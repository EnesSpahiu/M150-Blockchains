public class Main {
  public static void main(String[] args) {
    // Blockchain erstellen
    Blockchain blockchain = new Blockchain();

    // Blöcke hinzufügen
    blockchain.addBlock("Daten des Block 1");
    blockchain.addBlock("Daten des Block 2");

    // Blockchain durchlaufen und Hashes ausgeben
    for (Block block : blockchain.getChain()) {
      System.out.println("Block #" + block.getIndex());
      System.out.println("Timestamp: " + block.getTimestamp());
      System.out.println("Data: " + block.getData());
      System.out.println("Previous Hash: " + block.getPreviousHash());
      System.out.println("Hash: " + block.getHash());
      System.out.println();
    }
  }
}
