package es.mpr.plataformamensajeria.util;



import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.ServletActionRedirectResult;
import org.apache.struts2.dispatcher.ServletRedirectResult;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.Result;
import com.opensymphony.xwork2.ValidationAware;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * An Interceptor to preserve an actions ValidationAware messages across a
 * redirect result.
 *
 * It makes the assumption that you always want to preserve messages across a
 * redirect and restore them to the next action if they exist.
 *
 * The way this works is it looks at the result type after a action has executed
 * and if the result was a redirect (ServletRedirectResult) or a redirectAction
 * (ServletActionRedirectResult) and there were any errors, messages, or
 * fieldErrors they are stored in the session. Before the next action executes
 * it will check if there are any messages stored in the session and add them to
 * the next action.
 *
 */
public class RedirectMessageInterceptor extends MethodFilterInterceptor
{
    
    protected static final String UNCHECKED = "unchecked";

	protected static final String RAWTYPES = "rawtypes";

	/** Constante serialVersionUID. */
    private static final long  serialVersionUID    = -1847557437429753540L;

    /** Constante FIELD_ERRORS_KEY. */
    public static final String FIELD_ERRORS_KEY    = "RedirectMessageInterceptor_FieldErrors";
    
    /** Constante ACTION_ERRORS_KEY. */
    public static final String ACTION_ERRORS_KEY   = "RedirectMessageInterceptor_ActionErrors";
    
    /** Constante ACTION_MESSAGES_KEY. */
    public static final String ACTION_MESSAGES_KEY = "RedirectMessageInterceptor_ActionMessages";

    /**
     * Constructor de redirect message interceptor.
     */
    public RedirectMessageInterceptor() {
		// This method has to be empty.
    }

    /* (non-Javadoc)
     * @see com.opensymphony.xwork2.interceptor.MethodFilterInterceptor#doIntercept(com.opensymphony.xwork2.ActionInvocation)
     */
    public String doIntercept(ActionInvocation invocation) throws Exception {
        Object action = invocation.getAction();
        if (action instanceof ValidationAware) {
            before(invocation, (ValidationAware) action);
        }

        String result = invocation.invoke();

        if (action instanceof ValidationAware) {
            after(invocation, (ValidationAware) action);
        }
        return result;
    }

    /**
     * Retrieve the errors and messages from the session and add them to the
     * action.
     *
     * @param invocation the invocation
     * @param validationAware the validation aware
     * @throws Exception the exception
     */
    protected void before(ActionInvocation invocation,
                          ValidationAware validationAware)
        throws Exception {
        Map<String, ?> session = invocation.getInvocationContext().getSession();

        @SuppressWarnings({ UNCHECKED, RAWTYPES })
        Collection<String> actionErrors =
            (Collection) session.remove(ACTION_ERRORS_KEY);
        if (actionErrors != null && !actionErrors.isEmpty()) {
            for (String error : actionErrors) {
                validationAware.addActionError(error);
            }
        }

        @SuppressWarnings({ UNCHECKED, RAWTYPES })
        Collection<String> actionMessages =
            (Collection) session.remove(ACTION_MESSAGES_KEY);
        if (actionMessages != null && !actionMessages.isEmpty()) {
            for (String message : actionMessages) {
                validationAware.addActionMessage(message);
            }
        }

        @SuppressWarnings({ UNCHECKED, RAWTYPES })
        Map<String, List<String>> fieldErrors =
            (Map) session.remove(FIELD_ERRORS_KEY);
        if (fieldErrors != null && !fieldErrors.isEmpty()) {
            for (Map.Entry<String, List<String>> fieldError : fieldErrors.entrySet()) {
                for (String message : fieldError.getValue()) {
                    validationAware.addFieldError(fieldError.getKey(), message);
                }
            }
        }
    }

    /**
     * If the result is a redirect then store error and messages in the session.
     *
     * @param invocation the invocation
     * @param validationAware the validation aware
     * @throws Exception the exception
     */
    protected void after(ActionInvocation invocation,
                         ValidationAware validationAware)
        throws Exception {
        Result result = invocation.getResult();

        if (result != null
            && (result instanceof ServletRedirectResult ||
                result instanceof ServletActionRedirectResult)) {
            Map<String, Object> session = invocation.getInvocationContext().getSession();

            Collection<String> actionErrors = validationAware.getActionErrors();
            if (actionErrors != null && !actionErrors.isEmpty()) {
                session.put(ACTION_ERRORS_KEY, actionErrors);
            }

            Collection<String> actionMessages = validationAware.getActionMessages();
            if (actionMessages != null && !actionMessages.isEmpty()) {
                session.put(ACTION_MESSAGES_KEY, actionMessages);
            }

            Map<String, List<String>> fieldErrors = validationAware.getFieldErrors();
            if (fieldErrors != null && !fieldErrors.isEmpty()) {
                session.put(FIELD_ERRORS_KEY, fieldErrors);
            }
        }
    }
}