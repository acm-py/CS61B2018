public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private static final double G = 6.67e-11;
    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet P) {
        xxPos = P.xxPos;
        yyPos = P.yyPos;
        xxVel = P.xxVel;
        yyVel = P.yyVel;
        mass = P.mass;
        imgFileName = P.imgFileName;
    }

    public double calcDistance(Planet other) {
        double Distance = 0.0;
        double xDistance = 0.0;
        double yDistance = 0.0;
        xDistance = (other.xxPos - this.xxPos) * (other.xxPos - this.xxPos);
        yDistance = (other.yyPos - this.yyPos) * (other.yyPos - this.yyPos);
        Distance = (xDistance + yDistance);
        return SQR(Distance);
    }
    // return sqrt(double a)
    public static double SQR(double a) {
        double x1 = 1, x2;
        x2 = x1 / 2.0 + a / (2 * x1);
        while (Math.abs(x2 - x1) > 1e-4) {
            x1 = x2;
            x2 = x1 / 2.0 + a / (2 * x1);
        }
        return x2;
    }

    public double calcForceExertedBy(Planet other){
        double distance = this.calcDistance(other);
        double totalForce = (this.G*this.mass*other.mass)/(distance*distance);
        return totalForce;
    }

    // return x post force
    public double calcForceExertedByX(Planet other) {
        double totalForce = this.calcForceExertedBy(other);
        double xdistance = (other.xxPos - this.xxPos);
        double totaldistance = this.calcDistance(other);
        double Forcebyx = totalForce * (xdistance/totaldistance);
        return Forcebyx;
    }

    // return y post force
    public double calcForceExertedByY(Planet other) {
        double totalForce = this.calcForceExertedBy(other);
        double ydistance =  (other.yyPos - this.yyPos);
        double totaldistance = this.calcDistance(other);
        double Forcebyy = totalForce * (ydistance/totaldistance);
        return Forcebyy;
    }

    // return netx post force
    public double calcNetForceExertedByX(Planet[] other) {
        int length = other.length;
        double NetForeByX = 0.0;
        for (int i = 0; i < length; i++) {
            NetForeByX += this.calcForceExertedByX(other[i]);
        }
        return NetForeByX;
    }

    // return nety post force
    public double calcNetForceExertedByY(Planet[] other) {
        int length = other.length;
        double NetForeByY = 0.0;
        for (int i = 0; i < length; i++) {
            NetForeByY += this.calcForceExertedByY(other[i]);
        }
        return NetForeByY;
    }

    public void update(double dt, double fx, double fy) {
        double ax = fx / mass;
        double ay = fy / mass;
        xxVel += dt*ax;
        yyVel += dt*ay;
        xxPos += xxVel*dt;
        yyPos += yyVel*dt;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}
