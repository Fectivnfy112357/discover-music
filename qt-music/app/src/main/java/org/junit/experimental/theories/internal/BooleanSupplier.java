package org.junit.experimental.theories.internal;

import com.facebook.hermes.intl.Constants;
import java.util.Arrays;
import java.util.List;
import org.junit.experimental.theories.ParameterSignature;
import org.junit.experimental.theories.ParameterSupplier;
import org.junit.experimental.theories.PotentialAssignment;

/* loaded from: classes3.dex */
public class BooleanSupplier extends ParameterSupplier {
    @Override // org.junit.experimental.theories.ParameterSupplier
    public List<PotentialAssignment> getValueSources(ParameterSignature parameterSignature) {
        return Arrays.asList(PotentialAssignment.forValue("true", true), PotentialAssignment.forValue(Constants.CASEFIRST_FALSE, false));
    }
}
