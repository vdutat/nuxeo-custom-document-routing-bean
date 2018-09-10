package org.nuxeo.ecm.platform.routing.web;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.seam.annotations.Install;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Observer;
import org.jboss.seam.annotations.Scope;
import org.nuxeo.ecm.platform.task.TaskEventNames;

@Scope(CONVERSATION)
@Name("routingExtraActions")
@Install(precedence = Install.FRAMEWORK)
public class DocumentRoutingExtraActionsBean extends DocumentRoutingActionsBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private static final Log log = LogFactory.getLog(DocumentRoutingExtraActionsBean.class);

    @Observer(value = { TaskEventNames.WORKFLOW_TASK_COMPLETED }, create = false)
    public void resetCache() {
    	log.info("<resetCache> " + relatedRoutes);
        relatedRoutes = null;
        if (!hasRelatedRoute()) {
            webActions.resetTabList();
        }
    }
}
