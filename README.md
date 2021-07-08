# Yet another minecraft protocol
## Usage
1. To connect to server
    ```java
    FakePlayer player = new FakePlayer(name, ip, port);
    player.connect();
    ```
    
2. To listen to packets sent to player, class should extend ```PacketEventListener``` class and added to listeners of a fake player

    Example:
    ```java
    public class MainListener extends PacketEventListener { 
       
       public static void Main(String[] args) {
           FakePlayer player = new FakePlayer(name, ip, port);
           player.addListener(new MainListener());
           player.connect();
       }   
       
       @Override
       public void onPacketSent(PacketSentEvent event) {
            //code...
       }
       
       @Override
       public void onPacketReceived(PacketReceivedEvent event) {
            //code..
       }
   }
   ```
## Packets and other information

Javadocs are in progress.

All the packets and specifics
can be found at the [official minecraft protocol wiki](https://wiki.vg/Protocol)

## Currently done
1. Packet events (sent and recieved)

2. Logging in offline mode

3. Packet compression and decompression

4. Chat support

5. Chunk and blocks support (but `Material` enum is not fully done)

6. Position and look support