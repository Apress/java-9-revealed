module M {
    opens com.jdojo.claim.model;
    opens com.jdojo.policy.model to core.hibernate;
    opens com.jdojo.services to core.spring;
}
