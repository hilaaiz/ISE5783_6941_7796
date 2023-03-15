package primitives;

public class Vector extends Point {


    public Vector(double x ,double y, double z ){
        super(x,y,z);
    }

    Vector(Double3 xyz) {
        super(xyz);
    }


    /**
     *Addition of two vectors
     * @param myVector
     * @return
     */
    public Vector add(Vector myVector){
        return new Vector(xyz.add(myVector.xyz));
    }

    /**
     * Vector multiplication in scalar
     * @param num
     * @return
     */
    public Vector scale(double num){
        return new Vector(xyz.scale(num));
    }


    /**
     * A scalar force between two vectors
     * @param myVector
     * @return
     */
    public double dotProduct(Vector myVector){
        return xyz.d1*myVector.xyz.d1+xyz.d2*myVector.xyz.d2+xyz.d3*myVector.xyz.d3;
    }


    /**
     * A vector force between two vectors
     * @param myVector
     * @return
     */
    public Vector crossProduct(Vector myVector){
        return new Vector(xyz.d2*myVector.xyz.d3-xyz.d3*myVector.xyz.d2,
                -(xyz.d1*myVector.xyz.d3-xyz.d3*myVector.xyz.d1),
                xyz.d1*myVector.xyz.d2-xyz.d2*myVector.xyz.d1);
    }

    /**
     * return normalize vector
     * @param o
     * @return
     */
    public Vector normalize(){
        double dis= distance(this);
        return new Vector(xyz.d1/dis,xyz.d2/dis,xyz.d3/dis);
    }

    //return if the objects are equals
    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}
