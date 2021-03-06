// Autogenerated by convert_tests.py and process_polyglot.py.
// Do not edit this file directly.
// The template for this file is located at:
// ../../../../../templates/Test.java
package gen;

import com.rethinkdb.RethinkDB;
import com.rethinkdb.gen.exc.*;
import com.rethinkdb.gen.ast.*;
import com.rethinkdb.ast.ReqlAst;
import com.rethinkdb.model.MapObject;
import com.rethinkdb.model.OptArgs;
import com.rethinkdb.net.Connection;
import com.rethinkdb.net.Cursor;
import junit.framework.TestCase;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.junit.*;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.Instant;
import java.util.stream.LongStream;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.concurrent.TimeoutException;
import java.util.regex.Pattern;
import java.util.Collections;
import java.nio.charset.StandardCharsets;

import static gen.TestingCommon.*;
import gen.TestingFramework;

public class ChangefeedsIncludeStates {
    // Test `include_states`
    Logger logger = LoggerFactory.getLogger(ChangefeedsIncludeStates.class);
    public static final RethinkDB r = RethinkDB.r;
    public static final Table tbl = r.db("test").table("tbl");

    Connection<?> conn;
    public String hostname = TestingFramework.getConfig().getHostName();
    public int port = TestingFramework.getConfig().getPort();

    @Before
    public void setUp() throws Exception {
        logger.info("Setting up.");
        conn = TestingFramework.createConnection();
        try {
            r.dbCreate("test").run(conn);
            r.db("test").wait_().run(conn);
        }catch (Exception e){}
        try {
            r.db("test").tableCreate("tbl").run(conn);
            r.db("test").table(tbl).wait_().run(conn);
        }catch (Exception e){}
    }

    @After
    public void tearDown() throws Exception {
        logger.info("Tearing down.");
        r.db("rethinkdb").table("_debug_scratch").delete().run(conn);
        if(!conn.isOpen()){
            conn.close();
            conn = TestingFramework.createConnection();
        }
        r.db("test").tableDrop("tbl").run(conn);
        r.dbDrop("test").run(conn);
        conn.close(false);
    }

    // Autogenerated tests below

        @Test(timeout=120000)
    public void test() throws Exception {
                
        {
            // changefeeds/include_states.yaml line #4
            /* [{'state':'ready'}] */
            List expected_ = r.array(r.hashMap("state", "ready"));
            /* tbl.changes(squash=true, include_states=true).limit(1) */
            logger.info("About to run line #4: tbl.changes().optArg('squash', true).optArg('include_states', true).limit(1L)");
            Object obtained = runOrCatch(tbl.changes().optArg("squash", true).optArg("include_states", true).limit(1L),
                                          new OptArgs()
                                          ,conn);
            try {
                assertEquals(expected_, obtained);
            logger.info("Finished running line #4");
            } catch (Throwable ae) {
                logger.error("Whoops, got exception on line #4:" + ae.toString());
                if(obtained instanceof Throwable) {
                    ae.addSuppressed((Throwable) obtained);
                }
                throw ae;
            }
        }
        
        {
            // changefeeds/include_states.yaml line #9
            /* [{'state':'initializing'}, {'new_val':null}, {'state':'ready'}] */
            List expected_ = r.array(r.hashMap("state", "initializing"), r.hashMap("new_val", null), r.hashMap("state", "ready"));
            /* tbl.get(0).changes(squash=true, include_states=true, include_initial=true).limit(3) */
            logger.info("About to run line #9: tbl.get(0L).changes().optArg('squash', true).optArg('include_states', true).optArg('include_initial', true).limit(3L)");
            Object obtained = runOrCatch(tbl.get(0L).changes().optArg("squash", true).optArg("include_states", true).optArg("include_initial", true).limit(3L),
                                          new OptArgs()
                                          ,conn);
            try {
                assertEquals(expected_, obtained);
            logger.info("Finished running line #9");
            } catch (Throwable ae) {
                logger.error("Whoops, got exception on line #9:" + ae.toString());
                if(obtained instanceof Throwable) {
                    ae.addSuppressed((Throwable) obtained);
                }
                throw ae;
            }
        }
        
        {
            // changefeeds/include_states.yaml line #14
            /* [{'state':'initializing'}, {'state':'ready'}] */
            List expected_ = r.array(r.hashMap("state", "initializing"), r.hashMap("state", "ready"));
            /* tbl.order_by(index='id').limit(10).changes(squash=true, include_states=true, include_initial=true).limit(2) */
            logger.info("About to run line #14: tbl.orderBy().optArg('index', 'id').limit(10L).changes().optArg('squash', true).optArg('include_states', true).optArg('include_initial', true).limit(2L)");
            Object obtained = runOrCatch(tbl.orderBy().optArg("index", "id").limit(10L).changes().optArg("squash", true).optArg("include_states", true).optArg("include_initial", true).limit(2L),
                                          new OptArgs()
                                          ,conn);
            try {
                assertEquals(expected_, obtained);
            logger.info("Finished running line #14");
            } catch (Throwable ae) {
                logger.error("Whoops, got exception on line #14:" + ae.toString());
                if(obtained instanceof Throwable) {
                    ae.addSuppressed((Throwable) obtained);
                }
                throw ae;
            }
        }
        
        {
            // changefeeds/include_states.yaml line #19
            /* AnythingIsFine */
            Object expected_ = AnythingIsFine;
            /* tbl.insert({'id':1}) */
            logger.info("About to run line #19: tbl.insert(r.hashMap('id', 1L))");
            Object obtained = runOrCatch(tbl.insert(r.hashMap("id", 1L)),
                                          new OptArgs()
                                          ,conn);
            try {
                assertEquals(expected_, obtained);
            logger.info("Finished running line #19");
            } catch (Throwable ae) {
                logger.error("Whoops, got exception on line #19:" + ae.toString());
                if(obtained instanceof Throwable) {
                    ae.addSuppressed((Throwable) obtained);
                }
                throw ae;
            }
        }
        
        {
            // changefeeds/include_states.yaml line #21
            /* [{'state':'initializing'}, {'new_val':{'id':1}}, {'state':'ready'}] */
            List expected_ = r.array(r.hashMap("state", "initializing"), r.hashMap("new_val", r.hashMap("id", 1L)), r.hashMap("state", "ready"));
            /* tbl.order_by(index='id').limit(10).changes(squash=true, include_states=true, include_initial=true).limit(3) */
            logger.info("About to run line #21: tbl.orderBy().optArg('index', 'id').limit(10L).changes().optArg('squash', true).optArg('include_states', true).optArg('include_initial', true).limit(3L)");
            Object obtained = runOrCatch(tbl.orderBy().optArg("index", "id").limit(10L).changes().optArg("squash", true).optArg("include_states", true).optArg("include_initial", true).limit(3L),
                                          new OptArgs()
                                          ,conn);
            try {
                assertEquals(expected_, obtained);
            logger.info("Finished running line #21");
            } catch (Throwable ae) {
                logger.error("Whoops, got exception on line #21:" + ae.toString());
                if(obtained instanceof Throwable) {
                    ae.addSuppressed((Throwable) obtained);
                }
                throw ae;
            }
        }
        
        // changefeeds/include_states.yaml line #26
        // tblchanges = tbl.changes(squash=true, include_states=true)
        logger.info("Possibly executing: Changes tblchanges = (Changes) (tbl.changes().optArg('squash', true).optArg('include_states', true));");
        Object tblchanges = maybeRun((Changes) (tbl.changes().optArg("squash", true).optArg("include_states", true)), conn);
                
        {
            // changefeeds/include_states.yaml line #30
            /* AnythingIsFine */
            Object expected_ = AnythingIsFine;
            /* tbl.insert({'id':2}) */
            logger.info("About to run line #30: tbl.insert(r.hashMap('id', 2L))");
            Object obtained = runOrCatch(tbl.insert(r.hashMap("id", 2L)),
                                          new OptArgs()
                                          ,conn);
            try {
                assertEquals(expected_, obtained);
            logger.info("Finished running line #30");
            } catch (Throwable ae) {
                logger.error("Whoops, got exception on line #30:" + ae.toString());
                if(obtained instanceof Throwable) {
                    ae.addSuppressed((Throwable) obtained);
                }
                throw ae;
            }
        }
        
        {
            // changefeeds/include_states.yaml line #32
            /* [{'state':'ready'},{'new_val':{'id':2},'old_val':null}] */
            List expected_ = r.array(r.hashMap("state", "ready"), r.hashMap("new_val", r.hashMap("id", 2L)).with("old_val", null));
            /* fetch(tblchanges, 2) */
            logger.info("About to run line #32: fetch(tblchanges, 2L)");
            Object obtained = runOrCatch(fetch(tblchanges, 2L),
                                          new OptArgs()
                                          ,conn);
            try {
                assertEquals(expected_, obtained);
            logger.info("Finished running line #32");
            } catch (Throwable ae) {
                logger.error("Whoops, got exception on line #32:" + ae.toString());
                if(obtained instanceof Throwable) {
                    ae.addSuppressed((Throwable) obtained);
                }
                throw ae;
            }
        }
        
        // changefeeds/include_states.yaml line #35
        // getchanges = tbl.get(2).changes(include_states=true, include_initial=true)
        logger.info("Possibly executing: Changes getchanges = (Changes) (tbl.get(2L).changes().optArg('include_states', true).optArg('include_initial', true));");
        Object getchanges = maybeRun((Changes) (tbl.get(2L).changes().optArg("include_states", true).optArg("include_initial", true)), conn);
                
        {
            // changefeeds/include_states.yaml line #39
            /* AnythingIsFine */
            Object expected_ = AnythingIsFine;
            /* tbl.get(2).update({'a':1}) */
            logger.info("About to run line #39: tbl.get(2L).update(r.hashMap('a', 1L))");
            Object obtained = runOrCatch(tbl.get(2L).update(r.hashMap("a", 1L)),
                                          new OptArgs()
                                          ,conn);
            try {
                assertEquals(expected_, obtained);
            logger.info("Finished running line #39");
            } catch (Throwable ae) {
                logger.error("Whoops, got exception on line #39:" + ae.toString());
                if(obtained instanceof Throwable) {
                    ae.addSuppressed((Throwable) obtained);
                }
                throw ae;
            }
        }
        
        {
            // changefeeds/include_states.yaml line #41
            /* [{'state':'initializing'}, {'new_val':{'id':2}}, {'state':'ready'}, {'old_val':{'id':2},'new_val':{'id':2,'a':1}}] */
            List expected_ = r.array(r.hashMap("state", "initializing"), r.hashMap("new_val", r.hashMap("id", 2L)), r.hashMap("state", "ready"), r.hashMap("old_val", r.hashMap("id", 2L)).with("new_val", r.hashMap("id", 2L).with("a", 1L)));
            /* fetch(getchanges, 4) */
            logger.info("About to run line #41: fetch(getchanges, 4L)");
            Object obtained = runOrCatch(fetch(getchanges, 4L),
                                          new OptArgs()
                                          ,conn);
            try {
                assertEquals(expected_, obtained);
            logger.info("Finished running line #41");
            } catch (Throwable ae) {
                logger.error("Whoops, got exception on line #41:" + ae.toString());
                if(obtained instanceof Throwable) {
                    ae.addSuppressed((Throwable) obtained);
                }
                throw ae;
            }
        }
        
        // changefeeds/include_states.yaml line #44
        // limitchanges = tbl.order_by(index='id').limit(10).changes(include_states=true, include_initial=true)
        logger.info("Possibly executing: Changes limitchanges = (Changes) (tbl.orderBy().optArg('index', 'id').limit(10L).changes().optArg('include_states', true).optArg('include_initial', true));");
        Object limitchanges = maybeRun((Changes) (tbl.orderBy().optArg("index", "id").limit(10L).changes().optArg("include_states", true).optArg("include_initial", true)), conn);
                
        // changefeeds/include_states.yaml line #48
        // limitchangesdesc = tbl.order_by(index=r.desc('id')).limit(10).changes(include_states=true, include_initial=true)
        logger.info("Possibly executing: Changes limitchangesdesc = (Changes) (tbl.orderBy().optArg('index', r.desc('id')).limit(10L).changes().optArg('include_states', true).optArg('include_initial', true));");
        Object limitchangesdesc = maybeRun((Changes) (tbl.orderBy().optArg("index", r.desc("id")).limit(10L).changes().optArg("include_states", true).optArg("include_initial", true)), conn);
                
        {
            // changefeeds/include_states.yaml line #52
            /* AnythingIsFine */
            Object expected_ = AnythingIsFine;
            /* tbl.insert({'id':3}) */
            logger.info("About to run line #52: tbl.insert(r.hashMap('id', 3L))");
            Object obtained = runOrCatch(tbl.insert(r.hashMap("id", 3L)),
                                          new OptArgs()
                                          ,conn);
            try {
                assertEquals(expected_, obtained);
            logger.info("Finished running line #52");
            } catch (Throwable ae) {
                logger.error("Whoops, got exception on line #52:" + ae.toString());
                if(obtained instanceof Throwable) {
                    ae.addSuppressed((Throwable) obtained);
                }
                throw ae;
            }
        }
        
        {
            // changefeeds/include_states.yaml line #54
            /* [{'state':'initializing'}, {'new_val':{'id':1}}, {'new_val':{'a':1, 'id':2}}, {'state':'ready'}, {'old_val':null, 'new_val':{'id':3}}] */
            List expected_ = r.array(r.hashMap("state", "initializing"), r.hashMap("new_val", r.hashMap("id", 1L)), r.hashMap("new_val", r.hashMap("a", 1L).with("id", 2L)), r.hashMap("state", "ready"), r.hashMap("old_val", null).with("new_val", r.hashMap("id", 3L)));
            /* fetch(limitchanges, 5) */
            logger.info("About to run line #54: fetch(limitchanges, 5L)");
            Object obtained = runOrCatch(fetch(limitchanges, 5L),
                                          new OptArgs()
                                          ,conn);
            try {
                assertEquals(expected_, obtained);
            logger.info("Finished running line #54");
            } catch (Throwable ae) {
                logger.error("Whoops, got exception on line #54:" + ae.toString());
                if(obtained instanceof Throwable) {
                    ae.addSuppressed((Throwable) obtained);
                }
                throw ae;
            }
        }
        
        {
            // changefeeds/include_states.yaml line #57
            /* [{'state':'initializing'}, {'new_val':{'a':1, 'id':2}}, {'new_val':{'id':1}}, {'state':'ready'}, {'old_val':null, 'new_val':{'id':3}}] */
            List expected_ = r.array(r.hashMap("state", "initializing"), r.hashMap("new_val", r.hashMap("a", 1L).with("id", 2L)), r.hashMap("new_val", r.hashMap("id", 1L)), r.hashMap("state", "ready"), r.hashMap("old_val", null).with("new_val", r.hashMap("id", 3L)));
            /* fetch(limitchangesdesc, 5) */
            logger.info("About to run line #57: fetch(limitchangesdesc, 5L)");
            Object obtained = runOrCatch(fetch(limitchangesdesc, 5L),
                                          new OptArgs()
                                          ,conn);
            try {
                assertEquals(expected_, obtained);
            logger.info("Finished running line #57");
            } catch (Throwable ae) {
                logger.error("Whoops, got exception on line #57:" + ae.toString());
                if(obtained instanceof Throwable) {
                    ae.addSuppressed((Throwable) obtained);
                }
                throw ae;
            }
        }
    }
}
