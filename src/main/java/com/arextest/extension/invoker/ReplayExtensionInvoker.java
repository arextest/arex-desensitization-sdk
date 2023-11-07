package com.arextest.extension.invoker;


public interface ReplayExtensionInvoker {

  /**
   * check caseType by InvokerConstants#XXXCaseType.
   */
  boolean isSupported(String caseType);

  ReplayInvokeResult invoke(ReplayInvocation invocation);

  default int order() {
    return 1;
  }
}
