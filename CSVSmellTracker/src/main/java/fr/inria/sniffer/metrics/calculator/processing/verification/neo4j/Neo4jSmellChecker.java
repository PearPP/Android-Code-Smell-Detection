package fr.inria.sniffer.metrics.calculator.processing.verification.neo4j;

import fr.inria.sniffer.metrics.calculator.SmellCode;
import fr.inria.sniffer.metrics.calculator.processing.verification.SmellChecker;

import java.io.File;

public class Neo4jSmellChecker implements SmellChecker {
    private final SmellQuery smellQuery;

    public Neo4jSmellChecker(File dbPath) {
        smellQuery = new SmellQuery(dbPath);
    }

    @Override
    public boolean isActualRefactoring(SmellCode code, String instance, String commitHash) {
        switch (code) {
            case HMU:
            case IOD:
            case IWR:
            case MIM:
            case UCS:
            case UHA:
            case UIO:
                return smellQuery.isMethodExisting(commitHash, instance);
            case LIC:
            case NLMR:
                return smellQuery.isClassExisting(commitHash, instance);
            case IGS:
            case BLOB:
            case SAK:
            case LM:
            case CC:
        }
        return false;
    }
}
