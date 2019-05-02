module cz.honestcity.service {
	requires transitive cz.honestcity.model;
    requires spring.beans;
    requires spring.context;

    exports cz.honestcity.service.subject.exchange;
    exports cz.honestcity.service.suggestion;
    exports cz.honestcity.service.suggestion.exchange.closed;
    exports cz.honestcity.service.suggestion.exchange.create;
    exports cz.honestcity.service.suggestion.exchange.rate;
    exports cz.honestcity.service.suggestion.base;
    exports cz.honestcity.service.vote;
    exports cz.honestcity.service.vote.exchange.create;
    exports cz.honestcity.service.vote.exchange.closed;
    exports cz.honestcity.service.vote.exchange.rate;
    exports cz.honestcity.service.user;
    exports cz.honestcity.service.rate;
    exports cz.honestcity.service.subject;
}