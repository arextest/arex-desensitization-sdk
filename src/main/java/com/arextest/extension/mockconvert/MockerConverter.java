package com.arextest.extension.mockconvert;

import com.arextest.model.mock.MockCategoryType;
import com.arextest.model.mock.Mocker;

public interface MockerConverter {

  <T extends Mocker> T mockConvert(MockCategoryType category, T mocker) throws Exception;

}
