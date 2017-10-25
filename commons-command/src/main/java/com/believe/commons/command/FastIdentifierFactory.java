package com.believe.commons.command;

import org.axonframework.common.IdentifierFactory;

import java.util.UUID;

/**
 * <p> The describe </p>
 *
 * @author Li Xingping
 */
public class FastIdentifierFactory extends IdentifierFactory {

  private static final FastIdentifierFactory INSTANT = new FastIdentifierFactory();

  /**
   * TODO 切换实现方式
   */
  @Override
  public String generateIdentifier() {
    return UUID.randomUUID().toString().replace("-", "");
  }

}
