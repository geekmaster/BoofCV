/*
 * Copyright 2011 Peter Abeles
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

package gecv.factory.feature.describe;

import gecv.alg.feature.describe.OrientationAverage;
import gecv.alg.feature.describe.OrientationHistogram;
import gecv.alg.feature.describe.OrientationSlidingWindow;
import gecv.alg.feature.describe.impl.*;
import gecv.struct.image.ImageBase;
import gecv.struct.image.ImageFloat32;
import gecv.struct.image.ImageSInt16;
import gecv.struct.image.ImageSInt32;


/**
 * @author Peter Abeles
 */
@SuppressWarnings({"unchecked"})
public class FactoryRegionOrientationAlgs {

	public static <T extends ImageBase>
	OrientationHistogram<T> histogram( int numAngles , int radius , boolean weighted ,
									   Class<T> derivType )
	{
		OrientationHistogram<T> ret;

		if( derivType == ImageFloat32.class ) {
			ret = (OrientationHistogram<T>)new ImplOrientationHistogram_F32(numAngles,weighted);
		} else if( derivType == ImageSInt16.class ) {
			ret = (OrientationHistogram<T>)new ImplOrientationHistogram_S16(numAngles,weighted);
		} else if( derivType == ImageSInt32.class ) {
			ret = (OrientationHistogram<T>)new ImplOrientationHistogram_S32(numAngles,weighted);
		} else {
			throw new IllegalArgumentException("Unknown image type.");
		}

		ret.setRadius(radius);

		return ret;
	}

	public static <T extends ImageBase>
	OrientationAverage<T> average( int radius , boolean weighted , Class<T> derivType )
	{
		OrientationAverage<T> ret;

		if( derivType == ImageFloat32.class ) {
			ret = (OrientationAverage<T>)new ImplOrientationAverage_F32(weighted);
		} else if( derivType == ImageSInt16.class ) {
			ret = (OrientationAverage<T>)new ImplOrientationAverage_S16(weighted);
		} else if( derivType == ImageSInt32.class ) {
			ret = (OrientationAverage<T>)new ImplOrientationAverage_S32(weighted);
		} else {
			throw new IllegalArgumentException("Unknown image type.");
		}

		ret.setRadius(radius);

		return ret;
	}

	public static <T extends ImageBase>
	OrientationSlidingWindow<T> sliding( int numAngles, double windowSize , 
										 int radius , boolean weighted , Class<T> derivType )
	{
		OrientationSlidingWindow<T> ret;

		if( derivType == ImageFloat32.class ) {
			ret = (OrientationSlidingWindow<T>)new ImplOrientationSlidingWindow_F32(numAngles,windowSize,weighted);
		} else if( derivType == ImageSInt16.class ) {
			ret = (OrientationSlidingWindow<T>)new ImplOrientationSlidingWindow_S16(numAngles,windowSize,weighted);
		} else if( derivType == ImageSInt32.class ) {
			ret = (OrientationSlidingWindow<T>)new ImplOrientationSlidingWindow_S32(numAngles,windowSize,weighted);
		} else {
			throw new IllegalArgumentException("Unknown image type.");
		}

		ret.setRadius(radius);

		return ret;
	}
}
