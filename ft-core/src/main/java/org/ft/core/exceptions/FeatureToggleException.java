/*
 *    Copyright 2019-2030 codecuriosity
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package org.ft.core.exceptions;

/**
 * @author Prajwal Das
 */
public class FeatureToggleException extends RuntimeException
{
    public FeatureToggleException (String message)
    {
        super(message);
    }

    public static final FeatureToggleException FEATURE_NOT_FOUND = new FeatureToggleException(
        "Feature does not exist");

    public static final FeatureToggleException ENABLEMENT_FAILED = new FeatureToggleException(
        "Invalid id or invalid state ");

}
