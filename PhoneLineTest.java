import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class PhoneLineTest {
    PhoneLine phone;
    
    @BeforeEach
    void setUp() {
        phone = new PhoneLine();
    }
    
    @AfterEach
    void tearDown() {
        phone = null;
    }
    
    @Test
    public void testCase2() {
        // offHook/soundDialTone onHook/disconnectedLine onHook/nul
        assertEquals("soundDialTone", phone.offHook());
        assertEquals("disconnectedLine", phone.onHook());
    }
    
    @Test
    public void testCase3() {
        // offHook/soundDialTone offHook/nul onHook/disconnectedLine
        assertEquals("soundDialTone", phone.offHook());
        assertEquals("disconnectedLine", phone.onHook());
    }
    
    @Test
    public void testCase4() {
        // offHook/soundDialTone offHook/nul validNumber/findConnection
        assertEquals("soundDialTone", phone.offHook());
        assertEquals("findConnection", phone.validNumber());
    }
    
    @Test
    public void testCase5() throws InterruptedException {
        // offHook/soundDialTone offHook/nul I/I validNumber/findConnection
        assertEquals("soundDialTone", phone.offHook());
        Thread.sleep(100); // I/I - один такт
        assertEquals("findConnection", phone.validNumber());
    }
    
    @Test
    public void testCase6() throws InterruptedException {
        // offHook/soundDialTone offHook/nul I/I I/I validNumber/findConnection
        assertEquals("soundDialTone", phone.offHook());
        Thread.sleep(100); // I/I - первый такт
        Thread.sleep(100); // I/I - второй такт
        assertEquals("findConnection", phone.validNumber());
    }
    
    @Test
    public void testCase7() {
        // offHook/soundDialTone validNumber/findConnection onHook/disconnectedLine onHook/nul
        assertEquals("soundDialTone", phone.offHook());
        assertEquals("findConnection", phone.validNumber());
        assertEquals("disconnectedLine", phone.onHook());
    }
    
    @Test
    public void testCase8() {
        // offHook/soundDialTone validNumber/findConnection offHook/nul onHook/disconnectedLine
        assertEquals("soundDialTone", phone.offHook());
        assertEquals("findConnection", phone.validNumber());
        assertEquals("disconnectedLine", phone.onHook());
    }
    
    @Test
    public void testCase9() {
        // offHook/soundDialTone validNumber/findConnection offHook/nul validNumber/continues
        assertEquals("soundDialTone", phone.offHook());
        assertEquals("findConnection", phone.validNumber());
        assertEquals("continues", phone.validNumber());
    }
    
    @Test
    public void testCase10() {
        // offHook/soundDialTone validNumber/findConnection validNumber/continues onHook/disconnectedLine
        assertEquals("soundDialTone", phone.offHook());
        assertEquals("findConnection", phone.validNumber());
        assertEquals("continues", phone.validNumber());
        assertEquals("disconnectedLine", phone.onHook());
    }
    
    @Test
    public void testCase11() {
        // offHook/soundDialTone validNumber/findConnection validNumber/continues validNumber/continues
        assertEquals("soundDialTone", phone.offHook());
        assertEquals("findConnection", phone.validNumber());
        assertEquals("continues", phone.validNumber());
        assertEquals("continues", phone.validNumber());
    }
    
    @Test
    public void testCase12() {
        // offHook/soundDialTone validNumber/findConnection invalidNumber/continues onHook/disconnectedLine
        assertEquals("soundDialTone", phone.offHook());
        assertEquals("findConnection", phone.validNumber());
        assertEquals("continues", phone.invalidNumber());
        assertEquals("disconnectedLine", phone.onHook());
    }
    
    @Test
    public void testCase13() {
        // offHook/soundDialTone validNumber/findConnection invalidNumber/continues validNumber/continues
        assertEquals("soundDialTone", phone.offHook());
        assertEquals("findConnection", phone.validNumber());
        assertEquals("continues", phone.invalidNumber());
        assertEquals("continues", phone.validNumber());
    }
    
    @Test
    public void testCase14() throws InterruptedException {
        // offHook/soundDialTone validNumber/findConnection I/I onHook/disconnectedLine
        assertEquals("soundDialTone", phone.offHook());
        assertEquals("findConnection", phone.validNumber());
        Thread.sleep(100); // I/I
        assertEquals("disconnectedLine", phone.onHook());
    }
    
    @Test
    public void testCase15() throws InterruptedException {
        // offHook/soundDialTone validNumber/findConnection I/I validNumber/continues
        assertEquals("soundDialTone", phone.offHook());
        assertEquals("findConnection", phone.validNumber());
        Thread.sleep(100); // I/I
        assertEquals("continues", phone.validNumber());
    }
    
    @Test
    public void testCase16() {
        // offHook/soundDialTone invalidNumber/PlayMessage onHook/disconnectedLine onHook/nul
        assertEquals("soundDialTone", phone.offHook());
        assertEquals("PlayMessage", phone.invalidNumber());
        assertEquals("disconnectedLine", phone.onHook());
    }
    
    @Test
    public void testCase17() {
        // offHook/soundDialTone invalidNumber/PlayMessage offHook/nul onHook/disconnectedLine
        assertEquals("soundDialTone", phone.offHook());
        assertEquals("PlayMessage", phone.invalidNumber());
        assertEquals("disconnectedLine", phone.onHook());
    }
    
    @Test
    public void testCase18() {
        // offHook/soundDialTone invalidNumber/PlayMessage offHook/nul validNumber/slowBusyTone
        assertEquals("soundDialTone", phone.offHook());
        assertEquals("PlayMessage", phone.invalidNumber());
        assertEquals("slowBusyTone", phone.validNumber());
    }
    
    @Test
    public void testCase19() {
        // offHook/soundDialTone invalidNumber/PlayMessage validNumber/slowBusyTone onHook/disconnectedLine
        assertEquals("soundDialTone", phone.offHook());
        assertEquals("PlayMessage", phone.invalidNumber());
        assertEquals("slowBusyTone", phone.validNumber());
        assertEquals("disconnectedLine", phone.onHook());
    }
    
    @Test
    public void testCase20() {
        // offHook/soundDialTone invalidNumber/PlayMessage validNumber/slowBusyTone validNumber/slowBusyTone
        assertEquals("soundDialTone", phone.offHook());
        assertEquals("PlayMessage", phone.invalidNumber());
        assertEquals("slowBusyTone", phone.validNumber());
        assertEquals("slowBusyTone", phone.validNumber());
    }
    
    @Test
    public void testCase21() {
        // offHook/soundDialTone invalidNumber/PlayMessage invalidNumber/slowBusyTone onHook/disconnectedLine
        assertEquals("soundDialTone", phone.offHook());
        assertEquals("PlayMessage", phone.invalidNumber());
        assertEquals("slowBusyTone", phone.invalidNumber());
        assertEquals("disconnectedLine", phone.onHook());
    }
    
    @Test
    public void testCase22() {
        // offHook/soundDialTone invalidNumber/PlayMessage invalidNumber/slowBusyTone validNumber/slowBusyTone
        assertEquals("soundDialTone", phone.offHook());
        assertEquals("PlayMessage", phone.invalidNumber());
        assertEquals("slowBusyTone", phone.invalidNumber());
        assertEquals("slowBusyTone", phone.validNumber());
    }
    
    @Test
    public void testCase23() throws InterruptedException {
        // offHook/soundDialTone invalidNumber/PlayMessage I/I onHook/disconnectedLine
        assertEquals("soundDialTone", phone.offHook());
        assertEquals("PlayMessage", phone.invalidNumber());
        Thread.sleep(100); // I/I
        assertEquals("disconnectedLine", phone.onHook());
    }
    
    @Test
    public void testCase24() throws InterruptedException {
        // offHook/soundDialTone invalidNumber/PlayMessage I/I validNumber/slowBusyTone
        assertEquals("soundDialTone", phone.offHook());
        assertEquals("PlayMessage", phone.invalidNumber());
        Thread.sleep(100); // I/I
        assertEquals("slowBusyTone", phone.validNumber());
    }
    
    @Test
    public void testCase25() throws InterruptedException {
        // offHook/soundDialTone I/I onHook/disconnectedLine onHook/nul
        assertEquals("soundDialTone", phone.offHook());
        Thread.sleep(100); // I/I
        assertEquals("disconnectedLine", phone.onHook());
    }
    
    @Test
    public void testCase26() throws InterruptedException {
        // offHook/soundDialTone I/I offHook/nul onHook/disconnectedLine
        assertEquals("soundDialTone", phone.offHook());
        Thread.sleep(100); // I/I
        assertEquals("disconnectedLine", phone.onHook());
    }
    
    @Test
    public void testCase27() throws InterruptedException {
        // offHook/soundDialTone I/I offHook/nul validNumber/findConnection
        assertEquals("soundDialTone", phone.offHook());
        Thread.sleep(100); // I/I
        assertEquals("findConnection", phone.validNumber());
    }
    
    @Test
    public void testCase28() throws InterruptedException {
        // offHook/soundDialTone I/I offHook/nul I/I validNumber/findConnection
        assertEquals("soundDialTone", phone.offHook());
        Thread.sleep(100); // I/I - первый такт
        Thread.sleep(100); // I/I - второй такт
        assertEquals("findConnection", phone.validNumber());
    }
    
    @Test
    public void testCase30() throws InterruptedException {
        // offHook/soundDialTone I/I validNumber/findConnection onHook/disconnectedLine
        assertEquals("soundDialTone", phone.offHook());
        Thread.sleep(100); // I/I
        assertEquals("findConnection", phone.validNumber());
        assertEquals("disconnectedLine", phone.onHook());
    }
    
    @Test
    public void testCase31() throws InterruptedException {
        // offHook/soundDialTone I/I validNumber/findConnection validNumber/continues
        assertEquals("soundDialTone", phone.offHook());
        Thread.sleep(100); // I/I
        assertEquals("findConnection", phone.validNumber());
        assertEquals("continues", phone.validNumber());
    }
    
    @Test
    public void testCase32() throws InterruptedException {
        // offHook/soundDialTone I/I invalidNumber/PlayMessage onHook/disconnectedLine
        assertEquals("soundDialTone", phone.offHook());
        Thread.sleep(100); // I/I
        assertEquals("PlayMessage", phone.invalidNumber());
        assertEquals("disconnectedLine", phone.onHook());
    }
    
    @Test
    public void testCase33() throws InterruptedException {
        // offHook/soundDialTone I/I invalidNumber/PlayMessage validNumber/slowBusyTone
        assertEquals("soundDialTone", phone.offHook());
        Thread.sleep(100); // I/I
        assertEquals("PlayMessage", phone.invalidNumber());
        assertEquals("slowBusyTone", phone.validNumber());
    }
    
    @Test
    public void testCase34() throws InterruptedException {
        // offHook/soundDialTone I/I I/I onHook/disconnectedLine onHook/nul
        assertEquals("soundDialTone", phone.offHook());
        Thread.sleep(100); // I/I - первый такт
        Thread.sleep(100); // I/I - второй такт
        assertEquals("disconnectedLine", phone.onHook());
    }
    
    @Test
    public void testCase35() throws InterruptedException {
        // offHook/soundDialTone I/I I/I offHook/nul onHook/disconnectedLine
        assertEquals("soundDialTone", phone.offHook());
        Thread.sleep(100); // I/I - первый такт
        Thread.sleep(100); // I/I - второй такт
        assertEquals("disconnectedLine", phone.onHook());
    }
    
    @Test
    public void testCase36() throws InterruptedException {
        // offHook/soundDialTone I/I I/I offHook/nul validNumber/findConnection
        assertEquals("soundDialTone", phone.offHook());
        Thread.sleep(100); // I/I - первый такт
        Thread.sleep(100); // I/I - второй такт
        assertEquals("findConnection", phone.validNumber());
    }
    
    @Test
    public void testCase39() throws InterruptedException {
        // offHook/soundDialTone I/I I/I validNumber/findConnection onHook/disconnectedLine
        assertEquals("soundDialTone", phone.offHook());
        Thread.sleep(100); // I/I - первый такт
        Thread.sleep(100); // I/I - второй такт
        assertEquals("findConnection", phone.validNumber());
        assertEquals("disconnectedLine", phone.onHook());
    }
    
    @Test
    public void testCase40() throws InterruptedException {
        // offHook/soundDialTone I/I I/I validNumber/findConnection validNumber/continues
        assertEquals("soundDialTone", phone.offHook());
        Thread.sleep(100); // I/I - первый такт
        Thread.sleep(100); // I/I - второй такт
        assertEquals("findConnection", phone.validNumber());
        assertEquals("continues", phone.validNumber());
    }
    
    @Test
    public void testCase41() throws InterruptedException {
        // offHook/soundDialTone I/I I/I invalidNumber/PlayMessage onHook/disconnectedLine
        assertEquals("soundDialTone", phone.offHook());
        Thread.sleep(100); // I/I - первый такт
        Thread.sleep(100); // I/I - второй такт
        assertEquals("PlayMessage", phone.invalidNumber());
        assertEquals("disconnectedLine", phone.onHook());
    }
    
    @Test
    public void testCase42() throws InterruptedException {
        // offHook/soundDialTone I/I I/I invalidNumber/PlayMessage validNumber/slowBusyTone
        assertEquals("soundDialTone", phone.offHook());
        Thread.sleep(100); // I/I - первый такт
        Thread.sleep(100); // I/I - второй такт
        assertEquals("PlayMessage", phone.invalidNumber());
        assertEquals("slowBusyTone", phone.validNumber());
    }
    
    @Test
    public void testCase43_Timeout() throws InterruptedException {
        // offHook/soundDialTone I/I I/I I/I onHook/disconnectedLine
        assertEquals("soundDialTone", phone.offHook());
        Thread.sleep(100); // I/I - первый такт (Ready -> Ready_1)
        Thread.sleep(100); // I/I - второй такт (Ready_1 -> Ready_2)
        Thread.sleep(100); // I/I - третий такт (Ready_2 -> Warning) - Таймаут
        assertEquals("disconnectedLine", phone.onHook());
    }
    
    @Test
    public void testCase44_Timeout() throws InterruptedException {
        // offHook/soundDialTone I/I I/I I/I validNumber/slowBusyTone
        assertEquals("soundDialTone", phone.offHook());
        Thread.sleep(100); // I/I - первый такт (Ready -> Ready_1)
        Thread.sleep(100); // I/I - второй такт (Ready_1 -> Ready_2)
        Thread.sleep(100); // I/I - третий такт (Ready_2 -> Warning) - Таймаут
        assertEquals("slowBusyTone", phone.validNumber());
    }
}