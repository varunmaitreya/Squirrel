package org.aksw.simba.squirrel.deduplication.hashing.impl;

import org.aksw.simba.squirrel.deduplication.hashing.HashValue;
import org.aksw.simba.squirrel.deduplication.hashing.RDFHashFunction;
import org.apache.jena.graph.Triple;

import java.util.List;


/**
 * Use Min hash algorithm to compute hash values for triples.
 */
public class IntervalBasedMinHashFunction implements RDFHashFunction {

    /**
     * The number n for the 2^n Intervals for the Hashing.
     */
    private int powerNumberOfIntervals;

    /**
     * Constructor.
     *
     * @param powerNumberOfIntervals The powered number of Intervalls for the hashing.
     */
    public IntervalBasedMinHashFunction(int powerNumberOfIntervals) {
        this.powerNumberOfIntervals = powerNumberOfIntervals;
    }

    @Override
    public HashValue hash(List<Triple> triples) {
        Integer[] hashValues = new Integer[(int) Math.pow(2, powerNumberOfIntervals)];

        for (Triple triple : triples) {
            int hash = triple.hashCode();

            //TODO Change to bitshifting
            int i = hash >>> (32 - powerNumberOfIntervals);
            String lastBits = Integer.toBinaryString(hash).substring(0, powerNumberOfIntervals);
            int intervalNumber = Integer.parseInt(lastBits, 2);

            if (hashValues[intervalNumber] == null || hashValues[intervalNumber] > hash) {
                hashValues[intervalNumber] = hash;
            }
        }
        return new ArrayHashValue(hashValues);
    }

}
