package celeryroot.celery;

import java.util.Objects;

//stores the map identity of a game state in the defined grid
//split from the CellData because hashmap is a FUCK i hate coding!!!!!
public class CellPos {
    // X values should range from -300,000 to 300,000
    // Y values should range from -300,000 to 300,000
    // Z values should range up to 1,000,000
    // W values range up to 1 and 3
    public int x;
    public int y;
    public int z;
    public int w;

    public CellPos(){
    }

    public CellPos(int x, int y, int z, int w){
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }
    public CellPos(CellPos cellPos){
        this.x = cellPos.x;
        this.y = cellPos.y;
        this.z = cellPos.z;
        this.w = cellPos.w;
    }

    public void set(CellPos cellPos){
        this.x = cellPos.x;
        this.y = cellPos.y;
        this.z = cellPos.z;
        this.w = cellPos.w;
    }
    public void set(int x, int y, int z, int w){
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    @Override
    public String toString() {
        return "pos(" + x + ',' + y + ',' + z + ',' + w + ')';
    }

    public long toKey(){
        return pack(x,y,z,w);
    }

    public static long pack(int x, int y, int z, int w){
        return ((long)(x & 0xFFFFF) << 44) | ((long)(y & 0xFFFFF) << 24) | ((long)(z & 0xFFFFF) << 4) | (long)(x & 0xF);
    }

    public static void unpack(long key, CellPos out){
        int x = (int)((key >>> 44) & 0xFFFFF);
        int y = (int)((key >>> 24) & 0xFFFFF);
        int z = (int)((key >>> 4) & 0xFFFFF);
        int w = (int)(key & 0xF);
        out.set(x,y,z,w);
    }

    @Override
    public int hashCode() {
        long k = toKey();
        //Murmur3 hash stuff idk I found it online
        k ^= (k >>> 33);
        k *= 0xff51afd7ed558ccdL;
        k ^= (k >>> 33);
        k *= 0xc4ceb9fe1a85ec53L;
        k ^= (k >>> 33);
        return (int)k;
    }

    //god, if you're reading this, wtf am i supposed to do pls i am not cut out for writing essays for computers
    //@Override
    //public int hashCode() {
        //return (x & 0xFF) | ((y << 8) & 0xFF00) | ((z << 16) & 0XFF0000) | ((w << 24) & 0xFF000000);
        //return Objects.hash(x, y, z, w);
    //}

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;
        if(!(obj instanceof CellPos c))
            return false;
        return(x == c.x && y == c.y && z == c.z && w == c.w);
    }
}
