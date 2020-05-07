public String ScanQR(Bitmap bitmap){
        String value = null;
        GrayU8 image = ConvertBitmap.bitmapToGray(bitmap, (GrayU8)null, null);
        QrCodeDetector<GrayU8> detector = FactoryFiducial.qrcode (null, GrayU8.class);
        detector.process(image);
        List<QrCode> detections = detector.getDetections();
        for(QrCode Qr : detections){
            value = Qr.message;
            continue;
        }
        List<QrCode> failures = detector.getFailures();
        for( QrCode qr : failures ) {
            // If the 'cause' is ERROR_CORRECTION or later then it's probably a real QR Code that
            if( qr.failureCause.ordinal() < QrCode.Failure.ERROR_CORRECTION.ordinal() )
                value = qr.message;
            continue;
        }
        return value;
    }
