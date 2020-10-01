package com.company;

import edu.duke.*;

import java.io.File;

public class PerimeterRunner {
    public double getPerimeter(Shape shape){
        double totalPerim=0;
        Point prevPt = shape.getLastPoint();
        for(Point currPt:shape.getPoints()){
            double currDist = prevPt.distance(currPt);
            totalPerim+=currDist;
            prevPt=currPt;
        }
        return totalPerim;
    }
    public int getNumPoints(Shape shape){
        int i=0;
        for(Point ignored : shape.getPoints()) {
            i++;
        }
        return i;
    }

    public void testPerimeter(){
        FileResource fileResource = new FileResource();
        Shape shape = new Shape(fileResource);
        double length = getPerimeter(shape);
        System.out.println("Perimetre: " + length
                +"\ngetNumPoints: "+ getNumPoints(shape)
                +"\ngetAverageLength: "+ getAverageLength(shape)
                +"\ngetLargestSide: "+ getLargestSide(shape)
                +"\ngetLargestX: "+ getLargestX(shape));
    }
    public void getFileWithLargestPerimeter(){
        DirectoryResource dr = new DirectoryResource();
        double length=0.0;
        String filename = null;
        for (File f : dr.selectedFiles()) {
            FileResource fileResource = new FileResource(f);
            Shape shape = new Shape(fileResource);
            if(length<getPerimeter(shape)){
                length=getPerimeter(shape);
                filename=f.getName();}
        }
        System.out.println(length);
        System.out.println(filename);
    }

    public double getLargestSide(Shape shape){
        double dis=0.0;
        Point prevPt = shape.getLastPoint();
        for(Point currPt : shape.getPoints()) {
            if(dis<currPt.distance(prevPt)){
                dis = currPt.distance(prevPt);
                prevPt=currPt;
            }
        }
        return dis;
    }
    public double getLargestX(Shape shape){
        double x=0.0;
        for(Point point:shape.getPoints()){
            if(x<point.getX()){
                x=point.getX();
            }
        }
        return x;
    }

    public double getAverageLength(Shape shape){
        return(getPerimeter(shape)/getNumPoints(shape));
    }

    public static void main(String[] args) {
        PerimeterRunner GetPerimeter = new PerimeterRunner();
        GetPerimeter.testPerimeter();
        GetPerimeter.getFileWithLargestPerimeter();
    }
}