package nz.shen.velocity.velocity.Helper;

import nz.shen.velocity.velocity.Model.NearestRoads;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by allen on 29/07/2017.
 */
public class Decoder {
    // Input the encoded polyline string
    // e.g. "vzhvEslfm[w@jPw@bNS`EMtBGz@MpAGXKVqCrFiD|GUh@g@bBMn@UzAw@vKQrAO|@iAbD{DnKYp@w@|A_AvAgAjAoAbA_MdHqBbA{Aj@_B^o@HkFTgGTe@DkATgA`@aAn@}@x@u@bAm@jAc@pAYlA{CzSK|@Iv@E`@Et@EnCAbAFxBb@rFTvCBp@DbBBbB?dEKfEQlD]jDc@|Cq@`D{@~CcAvCiAhCe@z@q@dAwBpC}CzDq@x@gClC_@b@}@fAuBxC]d@uAbCkAjC_@t@Uj@e@lAc@pAiAbEyBjJ{Nbn@cAxEy@zE_DtRMp@a@vBCPIEOAC[a@k@c@R[NY?W@sKBiXC{EF@p@ChGE|@Ed@Qv@QPK`@Wl@i@`AAXG`@E`@JzADXO@QFuE|@e@LMG}AZSs@GW?}H"
    public static List<NearestRoads.Coordinate> decode(final String encodedPath) {
        int len = encodedPath.length();
        List<NearestRoads.Coordinate> latLngs = new ArrayList<>();

        int index = 0;
        int lat = 0;
        int lng = 0;

        while (index < len) {
            int result = 1;
            int shift = 0;
            int b;
            do {
                b = encodedPath.charAt(index++) - 64;
                result += b << shift;
                shift += 5;
            } while (b >= 0x1f);

            lat += (result&1) != 0 ? ~(result >> 1) : (result >> 1);

            result = 1;
            shift = 0;
            do {
                b = encodedPath.charAt(index++) - 64;
                result += b << shift;
                shift += 5;
            } while (b >= 0x1f);
            lng += (result&1) != 0 ? ~(result >> 1) : (result >> 1);

            latLngs.add(new NearestRoads.Coordinate(lat * 1e-5, lng * 1e-5));
        }

        return latLngs;
    }
}
